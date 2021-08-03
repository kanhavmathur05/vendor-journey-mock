package com.customerapplicationservice.controllers

import com.customerapplicationservice.modal.CustomerLoanApplication
import com.customerapplicationservice.services.CustomerLoanApplicationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
class CustomerLoanApplicationController(private val customerLoanApplicationService: CustomerLoanApplicationService) {

    @PostMapping("/save-application")
    fun saveCustomerApplication(@RequestBody customerLoanApplicationDetails: CustomerLoanApplication): ResponseEntity<Mono<CustomerLoanApplication>> = ResponseEntity.ok().body(customerLoanApplicationService.saveCustomerApplication(customerLoanApplicationDetails))

    @GetMapping("/get-application-status/{id}")
    fun getApplicationStatus(@PathVariable id: String): ResponseEntity<Mono<CustomerLoanApplication>> = ResponseEntity.ok().body(customerLoanApplicationService.getCustomerLoanApplicationStatus(id))
}