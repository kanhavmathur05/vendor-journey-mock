package com.customerapplicationservice.controllers

import com.customerapplicationservice.dto.CustomerOffer
import com.customerapplicationservice.modal.CustomerLoanApplication
import com.customerapplicationservice.services.CustomerLoanApplicationService
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.sql.Date

@RestController
class CustomerLoanApplicationController(private val customerLoanApplicationService: CustomerLoanApplicationService) {
    private  val webClient = WebClient.create("http://localhost:9999")

    @GetMapping("/check-offer/{id}/{db}")
    fun get(@PathVariable id:Number,@PathVariable db:Date): ResponseEntity<Mono<CustomerOffer>> = ResponseEntity.ok()
        .body(webClient.get().uri("/offerApi/$id/$db").header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .retrieve().bodyToMono(CustomerOffer::class.java))

    @PostMapping("/save-Offer")
    fun saveCustomerApplication(@RequestBody customerLoanApplicationDetails: CustomerLoanApplication): ResponseEntity<Mono<CustomerLoanApplication>> = ResponseEntity.ok().body(customerLoanApplicationService.saveCustomerApplication(customerLoanApplicationDetails))

    @GetMapping("/get-application-status/{id}")
    fun getApplicationStatus(@PathVariable id: String): ResponseEntity<Mono<CustomerLoanApplication>> = ResponseEntity.ok().body(customerLoanApplicationService.getCustomerLoanApplicationStatus(id))
}