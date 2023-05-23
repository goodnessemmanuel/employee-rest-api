package de.hexad.restapikotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinSpringBootRestApiApplication

fun main(args: Array<String>) {
	runApplication<KotlinSpringBootRestApiApplication>(*args)
}
