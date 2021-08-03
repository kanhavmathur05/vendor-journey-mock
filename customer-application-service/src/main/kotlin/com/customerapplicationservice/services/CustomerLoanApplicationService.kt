package com.customerapplicationservice.services

import com.customerapplicationservice.modal.CustomerLoanApplication
import com.customerapplicationservice.repositories.CustomerLoanApplicationRepo
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CustomerLoanApplicationService(private val customerLoanApplicationRepo: CustomerLoanApplicationRepo) {

    fun saveCustomerApplication(customerApplicationDetails: CustomerLoanApplication): Mono<CustomerLoanApplication> {
        print(customerApplicationDetails)
        customerApplicationDetails.applicationStatus=0
        print(customerApplicationDetails)
        return customerLoanApplicationRepo.save(customerApplicationDetails)
    }

    fun getCustomerLoanApplicationStatus(id: String): Mono<CustomerLoanApplication> {
        return customerLoanApplicationRepo.findById(id);
    }

}