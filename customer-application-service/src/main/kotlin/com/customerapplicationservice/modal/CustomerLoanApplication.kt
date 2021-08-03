package com.customerapplicationservice.modal

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class CustomerLoanApplication(
        @Id
        var id: String?,
        var contactNumber:Number,
        var loanAmount:Number,
        var emi:Number,
        var tenure:Number,
        var interestRate: Number,
        var stampDuty: Number,
        var processingFee: Number,
        var applicationStatus: Number?
        //TODO-Add other details
        )