package com.customerapplicationservice.modal

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class CustomerLoanApplication(

        @Id
        var id: String?,
        var fullName: String?,
        var panCard:String?,
        var email:String?,
        var employerName:String?,
        var residentialAddress:String?,
        var monthlySalary:String?,
        var city:String?,
        var gender:String?,
        var applicationStatus:String?
        )