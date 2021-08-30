//package com.customerapplicationservice
//
//import com.customerapplicationservice.dto.CustomerOffer
//import com.customerapplicationservice.dto.OfferApiRequest
//import org.junit.jupiter.api.Test
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.http.MediaType
//import org.springframework.test.web.reactive.server.WebTestClient
//import org.springframework.web.reactive.function.BodyInserters
//import reactor.core.publisher.Mono
//
//@SpringBootTest
//@AutoConfigureWebTestClient
//claCustomerLoanApplicationControllerTests {
//@Autowired
//lateinit var webTestClient: WebTestClient
//
////	@Test
////	fun getCustomerOfferSuccess(){
////		webTestClient.get().uri("http://localhost:8080/offerApi/9669935505/1998-09-29").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().is2xxSuccessful.expectBody().json(
////		"""{"id": null,
////			"loanAmount": 700000,
////			"emi": 16100,
////			"tenure": 5,
////			"rateOfInterest": 7.6,
////			"stampDuty": 1500,
////			"processingFee": 2500,
////			"netDisbursal:690000"
////			}"""
////		)
////	}
////
////	@Test
////	fun getCustomerOfferFailure(){
////		webTestClient.get().uri("http://localhost:8080/offerApi/9669935505/1998-09-29").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().is2xxSuccessful.expectBody().json(
////			"""{"id": null,
////			"loanAmount": 700000,
////			"emi": 16100,
////			"tenure": 5,
////			"rateOfInterest": 7.6,
////			"stampDuty": 1500,
////			"processingFee": 0000}"""
////		)
////	}
//
//	@Test
//	fun checkOffer1() {
////	var customerLoanApplicationController = CustomerLoanApplicationController()
//
////	var actualValue = customerLoanApplicationController.getM(OfferApiRequest(9669935505,"1998/09/29"))
//
//
//		//var expected =
//
//		/*	Mockito.`when`(webTestClient.post()
//            .uri("/check-offer")
//            .body(BodyInserters.fromValue(OfferApiRequest(9669935505,"1998-09-29")))
//            .accept(MediaType.APPLICATION_JSON)
//            .exchange()
//            .expectStatus().isOk
//            .returnResult(CustomerOffer::class.java)
//            .responseBody.blockFirst()
//
//        ).thenReturn(CustomerOffer(1,1,1,1,1,1,1))
//
//    */
//		webTestClient.post()
//				.uri("/check-offer")
//				.body(BodyInserters.fromValue(OfferApiRequest(9669935505,"1998-09-29")))
//				.accept(MediaType.APPLICATION_JSON)
//				.exchange()
//				.expectStatus().isOk
//				.expectBody().json("""{
//			"loanAmount": 700000,
//			"emi": 16100,
//			"tenure": 5,
//			"rateOfInterest": 7.6,
//			"stampDuty": 1500,
//			"processingFee": 2500,
//			"netDisbursal":690000
//			}""")
//
//	}
//	@Test
//	fun checkOffer2(){
//		val mono1: WebTestClient.ResponseSpec =
//				webTestClient.post()
//						.uri("/check-offer")
//						.body(BodyInserters.fromValue(OfferApiRequest(966993555505,"1998-09-29")))
//						.accept(MediaType.APPLICATION_JSON)
//						.exchange()
//						.expectStatus().is4xxClientError
//
//	}
//
//
//	@Test
//	fun getOffer(){
//		val customerOffer: CustomerOffer = CustomerOffer(1,1,1,1,1
//				,1,1)
//
//		val customerOfferMono: Mono<CustomerOffer> = Mono.just<CustomerOffer>(customerOffer)
//
//
//	}
//
//	@Test
//	fun getCustomerOfferSuccess(){
//		webTestClient.get().uri("http://localhost:8080/offerApi/9669935505/1998-09-29").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().is2xxSuccessful.expectBody().json(
//				"""{"id": null,
//			"loanAmount": 700000,
//			"emi": 16100,
//			"tenure": 5,
//			"rateOfInterest": 7.6,
//			"stampDuty": 1500,
//			"processingFee": 2500,
//			"netDisbursal:690000"
//			}"""
//		)
//	}
//
//	@Test
//	fun getCustomerOfferFailure(){
//		webTestClient.get().uri("http://localhost:8080/offerApi/9669935505/1998-09-29").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().is2xxSuccessful.expectBody().json(
//				"""{"id": null,
//			"loanAmount": 700000,
//			"emi": 16100,
//			"tenure": 5,
//			"rateOfInterest": 7.6,
//			"stampDuty": 1500,
//			"processingFee": 0000}"""
//		)
//	}
//}
