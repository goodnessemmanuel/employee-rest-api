package de.hexad.restapikotlin.controller

import de.hexad.restapikotlin.constant.RequestURIConstants.API_BASE_URL
import de.hexad.restapikotlin.constant.RequestURIConstants.HEALTH
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(API_BASE_URL)
class EmployeeController {

    @GetMapping(HEALTH)
    fun health() :ResponseEntity<String>{
        return ResponseEntity.ok("Hello! API Server")
    }
}