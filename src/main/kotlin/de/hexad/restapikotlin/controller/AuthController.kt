package de.hexad.restapikotlin.controller

import de.hexad.restapikotlin.constant.RequestURIConstant.LOGIN
import de.hexad.restapikotlin.constant.RequestURIConstant.REGISTER
import de.hexad.restapikotlin.domain.UserRequest
import de.hexad.restapikotlin.domain.UserResponse
import de.hexad.restapikotlin.exception.InvalidAuthenticationException
import de.hexad.restapikotlin.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

/**
 * authentication
 * class to protect
 * app integrity
 */
@RestController
class AuthController (private val userService: UserService) {
    private val logger = LoggerFactory.getLogger(this.javaClass.name)

    @PostMapping(REGISTER)
    fun register(@RequestBody userRequest: UserRequest) : ResponseEntity<UserResponse>{
        logger.info("register user request: {}", userRequest)
        val userResponse = userService.addUser(userRequest)
        val resourceURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(userResponse.id).toUri()
        logger.info("new user created: {}", userResponse)
        return ResponseEntity.created(resourceURI).body(userResponse)
    }

    @PostMapping(LOGIN)
    fun login(@RequestBody userRequest: UserRequest) :ResponseEntity<Any>{
        return try {
           val userResponse = userService.login(userRequest)
            logger.info("user login request response: {}", userResponse)
            ResponseEntity.ok(userResponse)
        } catch (ex: InvalidAuthenticationException){
           return ResponseEntity.badRequest().body(ex.message)
        }
    }
}