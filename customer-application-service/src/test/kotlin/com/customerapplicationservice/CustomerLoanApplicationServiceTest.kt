package com.customerapplicationservice
import com.customerapplicationservice.modal.CustomerLoanApplication
import com.customerapplicationservice.repository.ApplicationUpdateRepo
import com.customerapplicationservice.repository.CustomerLoanApplicationRepo
import com.customerapplicationservice.service.CustomerLoanApplicationService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@SpringBootTest
@AutoConfigureWebTestClient
@ExtendWith(MockitoExtension::class)
class CustomerLoanApplicationServiceClassTest {
    val applicationUpdateRepo = mockk<ApplicationUpdateRepo>()
    val customerLoanApplicationRepo = mockk<CustomerLoanApplicationRepo>()

    @Test
    fun `should get application status`(){
        val customerLoanApplicationService = CustomerLoanApplicationService(customerLoanApplicationRepo,applicationUpdateRepo)
        val expectedResult = Mono.just(CustomerLoanApplication(
            "", "Max Johnson", "HDBPS2121A", "max@gmail.com", "Google", "6 Chandni Chowk", "20000", "Delhi", "Male", "Initiated", "T", "Residential", "Axis Bank", 700000, 1250, 5, 12, 1250, 1200, 60000
        )
        )
        every { customerLoanApplicationRepo.findById("1") } returns expectedResult

        val actualResult = customerLoanApplicationService.getCustomerLoanApplicationStatus("1")

        Assertions.assertEquals(expectedResult,actualResult)
    }

    @Test
    fun `should return updated after application update success`() {
        val customerLoanApplicationService = CustomerLoanApplicationService(customerLoanApplicationRepo,applicationUpdateRepo)
        val expectedObject = CustomerLoanApplication(
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
        )
        val expected = "updated"
        every { applicationUpdateRepo.updateStatus("id1234id") } returns Flux.just(expectedObject)
        val actual = customerLoanApplicationService.updateApplicationStatus("id1234id")

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `should save application`(){
        val customerLoanApplicationService = CustomerLoanApplicationService(customerLoanApplicationRepo,applicationUpdateRepo)
        val body=CustomerLoanApplication(
            "", "Max Johnson", "HDBPS2121A", "max@gmail.com", "Google", "6 Chandni Chowk", "20000", "Delhi", "Male", "Initiated", "T", "Residential", "Axis Bank", 700000, 1250, 5, 12, 1250, 1200, 60000
        )
        val responseBody=CustomerLoanApplication(
            "123", "Max Johnson", "HDBPS2121A", "max@gmail.com", "Google", "6 Chandni Chowk", "20000", "Delhi", "Male", "Initiated", "T", "Residential", "Axis Bank", 700000, 1250, 5, 12, 1250, 1200, 60000
        )
        val expectedResult= responseBody

        every { customerLoanApplicationRepo.save(body) } returns Mono.just(expectedResult)
        val result = customerLoanApplicationService.saveCustomerApplication(body).block()
        Assertions.assertEquals(expectedResult, result)
    }
}