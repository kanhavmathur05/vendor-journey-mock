package com.customerapplicationservice

import com.customerapplicationservice.controller.CustomerLoanApplicationController
import com.customerapplicationservice.dto.ApplicationRequest
import com.customerapplicationservice.dto.CustomerOffer
import com.customerapplicationservice.dto.OfferApiRequest
import com.customerapplicationservice.modal.CustomerLoanApplication
import com.customerapplicationservice.service.CustomerLoanApplicationServiceClass
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.ResponseEntity
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@SpringBootTest
class CustomerLoanApplicationControllerTests {

    private val customerLoanApplicationService = mockk<CustomerLoanApplicationServiceClass>()
    private val customerLoanApplicationController = CustomerLoanApplicationController(customerLoanApplicationService)

    @Test
    fun `should return offers for particular user` (){

        var customerOffer : Flux<CustomerOffer> = Flux.just(
            CustomerOffer(
                "Axis Bank",
                "https://download.logo.wine/logo/Axis_Bank/Axis_Bank-Logo.wine.png",
                16100,
                12,
                700000,
                5,
                2500,
                1500,
                69000
            ), CustomerOffer(
                "HDFC Bank",
                "https://logos-world.net/wp-content/uploads/2020/11/HDFC-Bank-Emblem.png",
                20000,
                13,
                800000,
                5,
                3000,
                2000,
                75000
            ),
            CustomerOffer(
                "ICICI Bank",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/ICICI_Bank_Logo.svg/1200px-ICICI_Bank_Logo.svg.png",
                22000,
                14,
                850000,
                5,
                2800,
                1900,
                720000
            )
        )
        val offerApiRequest:OfferApiRequest = OfferApiRequest("9669935505","29-09-1998")
        every {customerLoanApplicationService.getOffers(OfferApiRequest("9669935505","29-09-1998"))  } returns customerOffer
        val actualResponse  = customerLoanApplicationController.getOffers(offerApiRequest)
        Assertions.assertEquals(customerOffer,actualResponse)

    }

    @Test
    fun `should save customer loan application`(){
        val applicationObject  = CustomerLoanApplication(
            "", "Max Johnson", "KJNJKNKJKN", "max@gmail.com", "Google", "6 Chandni Chowk", "20000", "Delhi", "Male", "Initiated", "T", "Residential", "Axis Bank", 1212, 12, 12, 12, 12, 12, 12
        )
        val returnedObject = Mono.just(CustomerLoanApplication("someId", "Max Johnson", "KJNJKNKJKN", "max@gmail.com", "Google", "6 Chandni Chowk", "20000", "Delhi", "Male", "Initiated", "T", "Residential", "Axis Bank", 1212, 12, 12, 12, 12, 12, 12)
        )
        val expectedResponse = ResponseEntity.ok().body(returnedObject)
        every { customerLoanApplicationService.saveCustomerApplication(applicationObject) } returns returnedObject

        val actualResult = customerLoanApplicationController.saveCustomerApplication(applicationObject)
        Assertions.assertEquals(expectedResponse,actualResult)
    }

    @Test
    fun `should return application status`(){
        val id = "1"
        val applicationResponse:Mono<CustomerLoanApplication> =Mono.just(
            CustomerLoanApplication(
                "1",
                "yash",
                "HDBPS2323A",
                "yash@gmail.com",
                "accenture",
                "10 shivaji park",
                "66333",
                "ujjain",
                "male",
                "InProgress",
                "Business",
                "Rented(With Family)",
                "Axis Bank",
                700000,
                16100,
                5,
                12,
                1500,
                2500,
                690000
            ))
        var expectedResponse = ResponseEntity.ok().body(applicationResponse)
        every { customerLoanApplicationService.getCustomerLoanApplicationStatus(id) } returns applicationResponse

        val actualResponse = customerLoanApplicationController.getApplicationStatus(ApplicationRequest(id))

        Assertions.assertEquals(expectedResponse,actualResponse)
    }

}