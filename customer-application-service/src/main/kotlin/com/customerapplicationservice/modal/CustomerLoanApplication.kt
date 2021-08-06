package com.customerapplicationservice.modal

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class CustomerLoanApplication(

        @Id
        var id: Int?,
        var contactNumber:Number,
        var dob: String,
        var loanAmount:Number,
        var emi:Number,
        var tenure:Number,
        var rateOfInterest: Number,
        var stampDuty: Number,
        var processingFee: Number
        //TODO-Add other details
        )