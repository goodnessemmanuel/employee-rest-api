package de.hexad.restapikotlin.controller

import de.hexad.restapikotlin.constant.RequestURIConstant.ADMIN
import de.hexad.restapikotlin.constant.RequestURIConstant.API_BASE_URL
import de.hexad.restapikotlin.constant.RequestURIConstant.HEALTH
import de.hexad.restapikotlin.constant.RequestURIConstant.USER
import org.springframework.http.ResponseEntity
//import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(API_BASE_URL)
class EmployeeController {

    //@PreAuthorize("hasAuthority('ROLE_User')")
    @GetMapping(HEALTH)
    fun health() :ResponseEntity<String>{
        return ResponseEntity.ok("Hello! API Server")
    }
    //@PreAuthorize("hasAuthority('ROLE_User')")
    @GetMapping(USER)
    fun welcomeUser() :ResponseEntity<String>{
        return ResponseEntity.ok("Hello User! API Server")
    }
    //@PreAuthorize("hasAuthority('APPROLE_Admin')")
    @GetMapping(ADMIN)
    fun adminUser() :ResponseEntity<String>{
        return ResponseEntity.ok("Hello admin! API Server")
    }
}