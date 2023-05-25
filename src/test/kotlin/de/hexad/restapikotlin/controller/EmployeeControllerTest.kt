package de.hexad.restapikotlin.controller

import de.hexad.restapikotlin.constant.RequestURIConstant.API_BASE_URL
import de.hexad.restapikotlin.constant.RequestURIConstant.USER
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.server.LocalServerPort

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class EmployeeControllerTest
@Autowired constructor(private val restTemplate: TestRestTemplate) {

    @LocalServerPort
    private var port: Int = 0

    private fun getURL(): String{
        return "http://localhost:$port/$API_BASE_URL"
    }

    @Test
    fun `should redirect to azure login url`() {
        val response = restTemplate.getForEntity<String>(
            "${getURL()}/$USER",
            String::class
        )

        assertEquals(302, response.statusCode.value())
        assertNull(response.body)
    }
}