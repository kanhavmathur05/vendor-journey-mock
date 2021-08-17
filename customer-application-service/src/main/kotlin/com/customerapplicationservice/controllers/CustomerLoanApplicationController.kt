package com.customerapplicationservice.controllers

import com.customerapplicationservice.dto.ApplicationRequest
import com.customerapplicationservice.dto.CustomerOffer
import com.customerapplicationservice.dto.OfferApiRequest
import com.customerapplicationservice.exceptions.CustomerOfferException
import com.customerapplicationservice.modal.CustomerLoanApplication
import com.customerapplicationservice.services.CustomerLoanApplicationService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@CrossOrigin("*")
class CustomerLoanApplicationController(private val customerLoanApplicationService: CustomerLoanApplicationService) {
    private val webClient = WebClient.create("http://localhost:9999")

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/check-offer")
    fun getM(@RequestBody offerApiRequest: OfferApiRequest): Flux<CustomerOffer> {
        logger.info("Inside getOffer method")
        logger.info("Offer Api request- " + offerApiRequest.contactNumber + " " + offerApiRequest.dob)

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

    @PostMapping("/save-application")
    fun saveCustomerApplication(@RequestBody customerLoanApplicationDetails: CustomerLoanApplication): ResponseEntity<Mono<CustomerLoanApplication>> {
        logger.info("Inside CustomerLoanApplicationController-Save Application method")
        logger.info(" " + customerLoanApplicationDetails.fullName + " " + customerLoanApplicationDetails.email + " " + customerLoanApplicationDetails.addressType + " " + customerLoanApplicationDetails.employmentType + " " + customerLoanApplicationDetails.gender + " " + customerLoanApplicationDetails.monthlySalary + " " + customerLoanApplicationDetails.city + " " + customerLoanApplicationDetails.residentialAddress)
        val response: Mono<CustomerLoanApplication>? = customerLoanApplicationService.saveCustomerApplication(customerLoanApplicationDetails)
        logger.info("Response:- $response")
        return ResponseEntity.ok().body(response)
    }

    @PostMapping("/get-application-status")
    fun getApplicationStatus(@RequestBody applicationRequest: ApplicationRequest): ResponseEntity<Mono<CustomerLoanApplication>> {
        logger.info("Inside getApplicationStatus method")
        logger.info("applicationRequest data " + applicationRequest.applicationId)
        val response: Mono<CustomerLoanApplication>? = customerLoanApplicationService.getCustomerLoanApplicationStatus(applicationRequest.applicationId)
        logger.info("Response:- $response")
        return ResponseEntity.ok().body(response)
    }

    @KafkaListener(topics = ["t4"], groupId = "myGroupId")
    fun getTopics(e: String) {
        logger.info("In pr $e")
        customerLoanApplicationService.updateApplicationStatus(e);
        // delayGenerateEvent(e)
    }

}