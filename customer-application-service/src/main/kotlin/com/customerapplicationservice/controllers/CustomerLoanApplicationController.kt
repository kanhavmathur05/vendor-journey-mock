package com.customerapplicationservice.controllers

import com.customerapplicationservice.dto.ApplicationRequest
import com.customerapplicationservice.dto.CustomerOffer
import com.customerapplicationservice.dto.OfferApiRequest
import com.customerapplicationservice.exceptions.CustomerOfferException
import com.customerapplicationservice.modal.CustomerLoanApplication
import com.customerapplicationservice.services.CustomerLoanApplicationService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@RestController
@CrossOrigin("*")
class CustomerLoanApplicationController(private val customerLoanApplicationService: CustomerLoanApplicationService) {
    private  val webClient = WebClient.create("http://localhost:9999")

    @PostMapping("/check-offer")
    fun getM(@RequestBody offerApiRequest: OfferApiRequest): Mono<Array<CustomerOffer>> {
        print(offerApiRequest.contactNumber)
        print(offerApiRequest.dob)
      //  customerLoanApplicationService.checkOffer()

        return webClient.post().uri("/offerApi")
            .body(BodyInserters.fromValue(offerApiRequest))
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .retrieve()
            .onStatus(
            HttpStatus::is4xxClientError){
                Mono.error(CustomerOfferException())
            }
            .bodyToMono(Array<CustomerOffer>::class.java)
    }

    @PostMapping("/save-application")
    fun saveCustomerApplication(@RequestBody customerLoanApplicationDetails: CustomerLoanApplication): ResponseEntity<Mono<CustomerLoanApplication>> {

        return  ResponseEntity.ok().body(customerLoanApplicationService.saveCustomerApplication(customerLoanApplicationDetails))
    }

    @PostMapping("/get-application-status")
    fun getApplicationStatus(@RequestBody applicationRequest: ApplicationRequest): ResponseEntity<Mono<CustomerLoanApplication>> = ResponseEntity.ok().body(customerLoanApplicationService.getCustomerLoanApplicationStatus(applicationRequest.applicationId))

    @KafkaListener(topics = ["t4"], groupId = "myGroupId")
    fun getTopics(e: String) :Unit{
        println("In pr $e")
        customerLoanApplicationService.updateApplicationStatus(e);
       // delayGenerateEvent(e)
    }

}