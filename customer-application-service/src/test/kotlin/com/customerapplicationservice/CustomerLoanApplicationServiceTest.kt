package com.customerapplicationservice

import com.customerapplicationservice.modal.CustomerLoanApplication
import com.customerapplicationservice.services.CustomerLoanApplicationService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import reactor.core.publisher.Mono

class CustomerLoanApplicationServiceTest {
    @BeforeEach
    internal fun setUp() {
//        val loanApplication:CustomerLoanApplication=Mockito.mock(CustomerLoanApplication::class.java)
//        loanApplication.id=12
//        loanApplication.loanAmount=12
//        loanApplication.emi=12
//        loanApplication.dob="121212"
//        loanApplication.processingFee=12
//        loanApplication.contactNumber=9675869786
//        loanApplication.rateOfInterest=12
//        loanApplication.stampDuty=12
//        loanApplication.tenure=12
//        print(loanApplication)
        val loanApplication:CustomerLoanApplication=CustomerLoanApplication(12,12,"121212",12,12,12,12,12,12)
                print("This================>"+loanApplication.loanAmount)
        val customerLoanApplicationService=Mockito.mock(CustomerLoanApplicationService::class.java)
        Mockito.`when`(customerLoanApplicationService.saveCustomerApplication(loanApplication)).thenReturn(Mono.just(CustomerLoanApplication(12,12,"121212",12,12,12,12,12,12)))
    }

    @Test
    internal fun submitApplicationServiceMethod() {
        val loanApplication:CustomerLoanApplication=CustomerLoanApplication(12,12,"121212",12,12,12,12,12,12)
        val customerLoanApplicationService:CustomerLoanApplicationService

        //        val response:Mono<CustomerLoanApplication>
//         response=
//        Mockito.verify(customerLoanApplicationService.saveCustomerApplication(loanApplication),Mono.just(loanApplication))
    }

    @AfterEach
    internal fun tearDown() {

    }
}