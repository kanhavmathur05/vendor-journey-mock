package com.customerapplicationservice.repository

import com.customerapplicationservice.modal.CustomerLoanApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
class ApplicationUpdateRepo (
@Autowired
private  val reactiveMongoOperations: ReactiveMongoOperations
){
    fun updateStatus(applicationId:String): Flux<CustomerLoanApplication>? {

        val query = Query.query(
            Criteria.where("id").isEqualTo(applicationId)
        )
        val applicationObject =  reactiveMongoOperations.find(query, CustomerLoanApplication::class.java)

        applicationObject.flatMap {
            if (it.panCard == "ABCD1234A" ){
                val updateStatus = Update().set("applicationStatus", "Decline")
                reactiveMongoOperations.updateFirst(query,updateStatus,CustomerLoanApplication::class.java)
            }else{
                val updateStatus = Update().set("applicationStatus", "Approved")
                reactiveMongoOperations.updateFirst(query,updateStatus,CustomerLoanApplication::class.java)
            }
        }.subscribe()

        return  reactiveMongoOperations.find(query,CustomerLoanApplication::class.java)
    }
}