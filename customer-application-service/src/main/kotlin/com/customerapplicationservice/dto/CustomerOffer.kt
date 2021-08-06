package com.customerapplicationservice.dto


data class CustomerOffer(
                        var loanAmount:Number,
                        var emi:Number,
                        var tenure:Number,
                        var rateOfInterest: Number,
                        var stampDuty: Number,
                        var processingFee: Number,
                        var netDisbursal:Number
                        )