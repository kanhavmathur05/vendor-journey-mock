package com.customerapplicationservice.modal

import lombok.Data
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
@Data
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
        var applicationStatus:String?,
        var employmentType:String?,
        var addressType:String?,
        var bankName: String?,
        var loanAmount:Number,
        var emi:Number,
        var tenure:Number,
        var rateOfInterest: Number,
        var stampDuty: Number,
        var processingFee: Number,
        var netDisbursal:Number
        )