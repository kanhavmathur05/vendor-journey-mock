package com.customerapplicationservice.services

import com.customerapplicationservice.modal.CustomerLoanApplication
import com.customerapplicationservice.repositories.ApplicationUpdateRepo
//import com.customerapplicationservice.repositories.ApplicationUpdateRepo
import com.customerapplicationservice.repositories.CustomerLoanApplicationRepo
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


    fun saveCustomerApplication(customerApplicationDetails: CustomerLoanApplication): Mono<CustomerLoanApplication> {
        customerApplicationDetails.applicationStatus = "InProgress"


        return customerLoanApplicationRepo.save(customerApplicationDetails).map { a ->
            delayGenerateEvent(a.id!!)
            a
        }
    }


    fun delayGenerateEvent(c: String) {
        print(c)
        //Thread.sleep(10000)
        kafkaTemplate?.send("t3", c)
    }

    fun getCustomerLoanApplicationStatus(id: String): Mono<CustomerLoanApplication> {
        val obj: Mono<CustomerLoanApplication> = customerLoanApplicationRepo.findById(id)
        return customerLoanApplicationRepo.findById(id);
    }

    fun updateApplicationStatus(id: String): String {
        //customerLoanApplicationRepo.changePassword(id,"Approved")
        print("In Update Application")

        print(customerLoanApplicationRepo2!!.updateStatus(id) )


/*        var c: Mono<CustomerLoanApplication> = customerLoanApplicationRepo.findById(id)

        c.map {
                a -> a.applicationStatus="Approved"
            a
        }
            .subscribe {a -> customerLoanApplicationRepo.save(a)
        }
*/
        return "done"

    }
}