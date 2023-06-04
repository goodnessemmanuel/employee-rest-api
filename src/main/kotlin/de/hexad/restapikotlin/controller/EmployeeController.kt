package de.hexad.restapikotlin.controller

//import org.springframework.security.access.prepost.PreAuthorize
import de.hexad.restapikotlin.constant.RequestURIConstant.ADD_EMPLOYEE
import de.hexad.restapikotlin.constant.RequestURIConstant.API_BASE_URL
import de.hexad.restapikotlin.constant.RequestURIConstant.EMPLOYEES
import de.hexad.restapikotlin.constant.RequestURIConstant.GET_EMPLOYEE
import de.hexad.restapikotlin.constant.RequestURIConstant.HEALTH
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(API_BASE_URL)
class EmployeeController {
    private val logger = LoggerFactory.getLogger(this.javaClass.name)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(HEALTH)
    fun health() :ResponseEntity<String>{
        return ResponseEntity.ok("Hello! API Server")
    }
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping(EMPLOYEES)
    fun getAllEmployees(authentication: Authentication) :ResponseEntity<String>{
        return ResponseEntity.ok("Hello admin, you can view All employees!")
    }
    @PreAuthorize("hasAuthority('employee:write')")
    @GetMapping(ADD_EMPLOYEE)
    fun addEmployee() :ResponseEntity<String>{
        return ResponseEntity.ok("Hello! You can add an employee")
    }
    @PreAuthorize("hasAuthority('employee:read')")
    @GetMapping(GET_EMPLOYEE)
    fun getEmployee(@PathVariable (name = "id") id: Int) :ResponseEntity<String>{
        return ResponseEntity.ok("Hello! You can retrieve an employee")
    }
}