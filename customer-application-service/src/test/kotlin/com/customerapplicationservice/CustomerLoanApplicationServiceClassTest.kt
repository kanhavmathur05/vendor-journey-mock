package com.customerapplicationservice

import com.customerapplicationservice.modal.CustomerLoanApplication
import com.customerapplicationservice.repository.ApplicationUpdateRepo
import com.customerapplicationservice.repository.CustomerLoanApplicationRepo
import com.customerapplicationservice.service.CustomerLoanApplicationServiceClass
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Mono


@SpringBootTest
@AutoConfigureWebTestClient
@ExtendWith(MockitoExtension::class)
class CustomerLoanApplicationServiceClassTest {
    val applicationUpdateRepo = mockk<ApplicationUpdateRepo>()
    val customerLoanApplicationRepo = mockk<CustomerLoanApplicationRepo>()

    @Test
    fun `should get application status`(){
        val customerLoanApplicationServiceClass = CustomerLoanApplicationServiceClass(customerLoanApplicationRepo,applicationUpdateRepo)
        val expected = Mono.just(CustomerLoanApplication("1", "Max Johnson", "KJNJKNKJKN", "max@gmail.com", "Google", "6 Chandni Chowk", "20000", "Delhi", "Male", "Initiated", "T", "Residential", "Axis Bank", 1212, 12, 12, 12, 12, 12, 12)
        )
        every { customerLoanApplicationRepo.findById("1") } returns expected

        val actualResult = customerLoanApplicationServiceClass.getCustomerLoanApplicationStatus("1")

        Assertions.assertEquals(expected,actualResult)
    }

    @Test
    fun `should return updated after application update`() {
        val customerLoanApplicationServiceClass = CustomerLoanApplicationServiceClass(customerLoanApplicationRepo,applicationUpdateRepo)
        every { applicationUpdateRepo.updateStatus("id1234id") } returns "updated"
        val result = customerLoanApplicationServiceClass.updateApplicationStatus("id1234id")
        Assertions.assertEquals("updated", result)
    }

    @Test
    fun `should save application`(){
        val customerLoanApplicationServiceClass = CustomerLoanApplicationServiceClass(customerLoanApplicationRepo,applicationUpdateRepo)
        val body=CustomerLoanApplication(null, "Max Johnson", "KJNJKNKJKN", "max@gmail.com", "Google", "6 Chandni Chowk", "20000", "Delhi", "Male", "Initiated", "", "Residential", "Axis Bank", 1212, 12, 12, 12, 12, 12, 12)
        val resBody=CustomerLoanApplication("id", "Max Johnson", "KJNJKNKJKN", "max@gmail.com", "Google", "6 Chandni Chowk", "20000", "Delhi", "Male", "Initiated", "", "Residential", "Axis Bank", 1212, 12, 12, 12, 12, 12, 12)

        val expectedResult: Mono<CustomerLoanApplication> = Mono.just(resBody)


        every { customerLoanApplicationRepo.save(body) } returns expectedResult
        val result = customerLoanApplicationServiceClass.saveCustomerApplication(body)

        Assertions.assertEquals(expectedResult, result)
    }


}