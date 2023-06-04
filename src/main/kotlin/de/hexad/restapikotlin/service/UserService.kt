package de.hexad.restapikotlin.service

import de.hexad.restapikotlin.domain.User
import de.hexad.restapikotlin.domain.dto.UserRequest
import de.hexad.restapikotlin.domain.dto.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
interface UserService {
    fun findUserById(userId :String) :User
    fun addUser(userRequest: UserRequest) : UserResponse
    fun login(userRequest: UserRequest) : UserResponse?
    fun createTokenToAccessApi(authentication: Authentication?) : ResponseEntity<Any>
}