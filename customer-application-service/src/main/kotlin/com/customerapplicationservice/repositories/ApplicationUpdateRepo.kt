package com.customerapplicationservice.repositories

import com.customerapplicationservice.modal.CustomerLoanApplication
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Repository

@Repository
class ApplicationUpdateRepo (
    @Autowired
    private  val reactiveMongoOperations: ReactiveMongoOperations
){
    fun updateStatus(id:String): String {

        val query = Query.query(
            Criteria.where("id").isEqualTo(id)
        )
     val c =  reactiveMongoOperations.find(query, CustomerLoanApplication::class.java)

       val updateStatus = Update().set("applicationStatus", "Approved")

    val upda =  reactiveMongoOperations.updateFirst(query,updateStatus,CustomerLoanApplication::class.java)
        upda.subscribe()
        var d = reactiveMongoOperations.find(query,CustomerLoanApplication::class.java)
        d.subscribe { a -> print(a.applicationStatus)}
        return "Approved"
    }
}