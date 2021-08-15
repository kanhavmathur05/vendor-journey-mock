package com.customerapplicationservice.dto


data class CustomerOffer(
        var bankName: String?,
        var bankIcon: String?,
                        var loanAmount:Number,
                        var emi:Number,
                        var tenure:Number,
                        var rateOfInterest: Number,
                        var stampDuty: Number,
                        var processingFee: Number,
                        var netDisbursal:Number

                        )