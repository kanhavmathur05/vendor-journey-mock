package com.customerapplicationservice.services

import com.customerapplicationservice.modal.CustomerLoanApplication
import com.customerapplicationservice.repositories.ApplicationUpdateRepo
//import com.customerapplicationservice.repositories.ApplicationUpdateRepo
import com.customerapplicationservice.repositories.CustomerLoanApplicationRepo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
var id = ""

@Service
class CustomerLoanApplicationService(private val customerLoanApplicationRepo: CustomerLoanApplicationRepo, private val customerLoanApplicationRepo2:ApplicationUpdateRepo)
{
    @Autowired
    private val kafkaTemplate: KafkaTemplate<String, String>? = null

    private val logger: Logger = LoggerFactory.getLogger(CustomerLoanApplicationService::class.java)

    fun saveCustomerApplication(customerApplicationDetails: CustomerLoanApplication): Mono<CustomerLoanApplication> {
        logger.info("Inside Service - Save Customer Application method")
        logger.info(" " + customerApplicationDetails.fullName + " " + customerApplicationDetails.email + " " + customerApplicationDetails.addressType + " " + customerApplicationDetails.employmentType + " " + customerApplicationDetails.gender + " " + customerApplicationDetails.monthlySalary + " " + customerApplicationDetails.city + " " + customerApplicationDetails.residentialAddress)
        customerApplicationDetails.applicationStatus = "InProgress"
        return customerLoanApplicationRepo.save(customerApplicationDetails).map { a ->
            delayGenerateEvent(a.id!!)
            a
        }
    }

    fun delayGenerateEvent(c: String) {
        logger.info("Inside delayGenerateEvent method with id: $c")
        //Thread.sleep(10000)
        kafkaTemplate?.send("t3", c)
    }

    fun getCustomerLoanApplicationStatus(id: String): Mono<CustomerLoanApplication> {
        logger.info("Inside getCustomerLoanApplicationStatus with ID: $id")
        val obj: Mono<CustomerLoanApplication> = customerLoanApplicationRepo.findById(id)
        return customerLoanApplicationRepo.findById(id);
    }

    fun updateApplicationStatus(id: String): String {
        //customerLoanApplicationRepo.changePassword(id,"Approved")
        logger.info("Inside updateApplicationStatus method with id: $id")
        print("In Update Application")
        print(customerLoanApplicationRepo2!!.updateStatus(id) )
        return "done"

    }
}