package com.customerapplicationservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CustomerApplicationServiceApplication

fun main(args: Array<String>) {
	runApplication<CustomerApplicationServiceApplication>(*args)
}
