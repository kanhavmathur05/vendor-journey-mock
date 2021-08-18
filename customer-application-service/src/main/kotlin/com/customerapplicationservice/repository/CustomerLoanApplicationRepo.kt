package com.customerapplicationservice.repository

import com.customerapplicationservice.modal.CustomerLoanApplication
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository


@Repository
interface CustomerLoanApplicationRepo: ReactiveMongoRepository<CustomerLoanApplication,String> {

}