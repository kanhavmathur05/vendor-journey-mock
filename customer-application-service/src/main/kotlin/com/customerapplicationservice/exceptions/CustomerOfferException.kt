package com.customerapplicationservice.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class CustomerOfferException :RuntimeException(){
@ExceptionHandler(CustomerOfferException::class)
fun handleCustomerOfferException(): ResponseEntity<Any> {
    val body: MutableMap<String , Any>  = LinkedHashMap()
    body["message"]="No Offer Found"
    return ResponseEntity(body,HttpStatus.NOT_FOUND)
}

}