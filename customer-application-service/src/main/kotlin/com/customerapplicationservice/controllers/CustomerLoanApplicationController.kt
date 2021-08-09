package com.customerapplicationservice.controllers

import com.customerapplicationservice.dto.CustomerOffer
import com.customerapplicationservice.dto.OfferApiRequest
import com.customerapplicationservice.exceptions.CustomerOfferException
import com.customerapplicationservice.modal.CustomerLoanApplication
import com.customerapplicationservice.services.CustomerLoanApplicationService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import java.sql.Date

@RestController
class CustomerLoanApplicationController(private val customerLoanApplicationService: CustomerLoanApplicationService) {
    private  val webClient = WebClient.create("http://localhost:9999")

    @PostMapping("/check-offer")
    fun getM(@RequestBody offerApiRequest: OfferApiRequest): Mono<CustomerOffer> {
        print(offerApiRequest.contactNumber)
        print(offerApiRequest.dob)


        return webClient.post().uri("/offerApi")
            .body(BodyInserters.fromValue(offerApiRequest))
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .retrieve()
            .onStatus(
            HttpStatus::is4xxClientError){
                Mono.error(CustomerOfferException())
            }
            .bodyToMono(CustomerOffer::class.java)
    }

    @PostMapping("/save-Offer")
    fun saveCustomerApplication(@RequestBody customerLoanApplicationDetails: CustomerLoanApplication): ResponseEntity<Mono<CustomerLoanApplication>> = ResponseEntity.ok().body(customerLoanApplicationService.saveCustomerApplication(customerLoanApplicationDetails))

    @GetMapping("/get-application-status/{id}")
    fun getApplicationStatus(@PathVariable id: String): ResponseEntity<Mono<CustomerLoanApplication>> = ResponseEntity.ok().body(customerLoanApplicationService.getCustomerLoanApplicationStatus(id))
}