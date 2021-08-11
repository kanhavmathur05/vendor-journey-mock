package com.customerapplicationservice.services

import com.customerapplicationservice.modal.CustomerLoanApplication
import com.customerapplicationservice.repositories.CustomerLoanApplicationRepo
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Service
class CustomerLoanApplicationService(private val customerLoanApplicationRepo: CustomerLoanApplicationRepo) {

    fun saveCustomerApplication(customerApplicationDetails: CustomerLoanApplication): Mono<CustomerLoanApplication> {
        customerApplicationDetails.applicationStatus="InProgress"
        return customerLoanApplicationRepo.save(customerApplicationDetails)
    }

    fun getCustomerLoanApplicationStatus(id: String): Mono<CustomerLoanApplication> {
        val obj:Mono<CustomerLoanApplication> = customerLoanApplicationRepo.findById(id)
        return customerLoanApplicationRepo.findById(id);
    }

}