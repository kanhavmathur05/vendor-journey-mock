package com.customerapplicationservice

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest
@AutoConfigureWebTestClient
class CustomerLoanApplicationControllerTests {
@Autowired
lateinit var webTestClient: WebTestClient

	@Test
	fun getCustomerOfferSuccess(){
		webTestClient.get().uri("http://localhost:8080/offerApi/9669935505/1998-09-29").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().is2xxSuccessful.expectBody().json(
		"""{"id": null,
			"loanAmount": 700000,
			"emi": 16100,
			"tenure": 5,
			"rateOfInterest": 7.6,
			"stampDuty": 1500,
			"processingFee": 2500,
			"netDisbursal:690000"
			}"""
		)
	}

	@Test
	fun getCustomerOfferFailure(){
		webTestClient.get().uri("http://localhost:8080/offerApi/9669935505/1998-09-29").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().is2xxSuccessful.expectBody().json(
			"""{"id": null,
			"loanAmount": 700000,
			"emi": 16100,
			"tenure": 5,
			"rateOfInterest": 7.6,
			"stampDuty": 1500,
			"processingFee": 0000}"""
		)
	}
}
