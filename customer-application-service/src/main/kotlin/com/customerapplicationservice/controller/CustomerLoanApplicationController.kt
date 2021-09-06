package com.customerapplicationservice.controller

import com.customerapplicationservice.dto.ApplicationRequest
import com.customerapplicationservice.dto.CustomerOffer
import com.customerapplicationservice.dto.OfferApiRequest
import com.customerapplicationservice.modal.CustomerLoanApplication
import com.customerapplicationservice.service.CustomerLoanApplicationServiceClass
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@CrossOrigin("*")
class CustomerLoanApplicationController(private val customerLoanApplicationService: CustomerLoanApplicationServiceClass) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping("/get-offers")
    fun getOffers(@RequestBody offerApiRequest: OfferApiRequest): Flux<CustomerOffer> {

        logger.info("Inside getOffers method "+"Offer Api request- " + offerApiRequest.contactNumber + " " + offerApiRequest.dob)
        return customerLoanApplicationService.getOffers(offerApiRequest)

    }

    @PostMapping("/save-application")
    fun saveCustomerApplication(@RequestBody customerLoanApplicationDetails: CustomerLoanApplication): ResponseEntity<Mono<CustomerLoanApplication>> {
        logger.info("Inside CustomerLoanApplicationController-Save Application method"+" " + customerLoanApplicationDetails.fullName + " " + customerLoanApplicationDetails.email + " " + customerLoanApplicationDetails.addressType + " " + customerLoanApplicationDetails.employmentType + " " + customerLoanApplicationDetails.gender + " " + customerLoanApplicationDetails.monthlySalary + " " + customerLoanApplicationDetails.city + " " + customerLoanApplicationDetails.residentialAddress)
        val response: Mono<CustomerLoanApplication>? = customerLoanApplicationService.saveCustomerApplication(customerLoanApplicationDetails)
        return ResponseEntity.ok().body(response)
    }

    @PostMapping("/get-application-status")
    fun getApplicationStatus(@RequestBody applicationRequest: ApplicationRequest): ResponseEntity<Mono<CustomerLoanApplication>> {
        logger.info("Inside getApplicationStatus method " +"applicationRequest data " + applicationRequest.applicationId)
        val response: Mono<CustomerLoanApplication>? = customerLoanApplicationService.getCustomerLoanApplicationStatus(applicationRequest.applicationId)
        return ResponseEntity.ok().body(response)
    }

    @KafkaListener(topics = ["updateApplicationStatus"], groupId = "myGroupId")
    fun updateApplicationStatus(applicationId: String) {
        logger.info("In updateApplicationStatus Method  $applicationId")
        customerLoanApplicationService.updateApplicationStatus(applicationId);
    }

}
