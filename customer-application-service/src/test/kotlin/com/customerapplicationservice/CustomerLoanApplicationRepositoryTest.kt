package com.customerapplicationservice

import com.customerapplicationservice.repository.ApplicationUpdateRepo
import com.customerapplicationservice.repository.CustomerLoanApplicationRepo
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class CustomerLoanApplicationRepositoryTest {
    val applicationUpdateRepo = mockk<ApplicationUpdateRepo>()
    val customerLoanApplicationRepo = mockk<CustomerLoanApplicationRepo>()
    @Test
    fun `should update application status repository `(){
        val applicationId = "1"
        val expected = "approved"
        every {  applicationUpdateRepo.updateStatus(applicationId)
        } returns expected
        val actual = applicationUpdateRepo.updateStatus(applicationId)
        Assertions.assertEquals(expected,actual)
    }

}