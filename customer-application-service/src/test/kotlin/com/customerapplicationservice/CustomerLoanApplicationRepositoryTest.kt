package com.customerapplicationservice

import com.customerapplicationservice.modal.CustomerLoanApplication
import com.customerapplicationservice.repository.ApplicationUpdateRepo
import com.customerapplicationservice.repository.CustomerLoanApplicationRepo
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux

class CustomerLoanApplicationRepositoryTest {
    val applicationUpdateRepo = mockk<ApplicationUpdateRepo>()
    @Test
    fun `should update application status repository `(){
        val applicationId = "1"
        val expected = Flux.just(
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
            "Approved",
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
        )
        every {  applicationUpdateRepo.updateStatus(applicationId)
        } returns expected
        val actual = applicationUpdateRepo.updateStatus(applicationId)
        Assertions.assertEquals(expected,actual)
    }

}