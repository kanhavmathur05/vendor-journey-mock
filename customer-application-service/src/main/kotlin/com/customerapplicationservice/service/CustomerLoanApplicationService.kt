package com.customerapplicationservice.service

import com.customerapplicationservice.dto.CustomerOffer
import com.customerapplicationservice.dto.OfferApiRequest
import com.customerapplicationservice.exception.CustomerOfferException
import com.customerapplicationservice.modal.CustomerLoanApplication
import com.customerapplicationservice.repository.ApplicationUpdateRepo
import com.customerapplicationservice.repository.CustomerLoanApplicationRepo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Service
class CustomerLoanApplicationService(
    private val customerLoanApplicationRepo: CustomerLoanApplicationRepo
    ,
    private val customerLoanApplicationRepo2:ApplicationUpdateRepo
)
{
    @Autowired
    private val kafkaTemplate: KafkaTemplate<String, String>? = null

    private val logger: Logger = LoggerFactory.getLogger(CustomerLoanApplicationService::class.java)



    fun getOffers(offerApiRequest:OfferApiRequest): Flux<CustomerOffer> {
        val webClient = WebClient.create("http://5ro53.mocklab.io")

        logger.info("Inside getoffers method ${offerApiRequest.contactNumber} ${offerApiRequest.dob}")
        return webClient.post().uri("/offerApi")
            .body(BodyInserters.fromValue(offerApiRequest))
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .retrieve()
            .onStatus(
                HttpStatus::is4xxClientError) {
                Mono.error(CustomerOfferException())
            }
            .bodyToFlux(CustomerOffer::class.java)

    }


    fun saveCustomerApplication(customerApplicationDetails: CustomerLoanApplication): Mono<CustomerLoanApplication> {
        logger.info("Inside Service - Save Customer Application method" +" " + customerApplicationDetails.fullName + " " + customerApplicationDetails.email + " " + customerApplicationDetails.addressType + " " + customerApplicationDetails.employmentType + " " + customerApplicationDetails.gender + " " + customerApplicationDetails.monthlySalary + " " + customerApplicationDetails.city + " " + customerApplicationDetails.residentialAddress)
        var  id =""
        customerApplicationDetails.applicationStatus = "InProgress"

        return customerLoanApplicationRepo.save(customerApplicationDetails).map {
            it.id?.let { it1 -> generateDelayEvent(it1) }
            it
        }
    }

    fun generateDelayEvent(applicationId: String) {

        logger.info("Inside delayGenerateEvent method with id: $applicationId")
        kafkaTemplate?.send("receivedApplicationId", applicationId)
    }

    fun getCustomerLoanApplicationStatus(applicationId: String): Mono<CustomerLoanApplication> {
        logger.info("Inside getCustomerLoanApplicationStatus with ID: $applicationId")
        return customerLoanApplicationRepo.findById(applicationId)//.doOnError(throw NoApplicationStatus()) //.switchIfEmpty { throw NoApplicationStatus() }
    }

    fun updateApplicationStatus(applicationId: String): String {
        logger.info("Inside updateApplicationStatus method with id: $applicationId")
        customerLoanApplicationRepo2!!.updateStatus(applicationId)
        return "updated"
    }
}