package de.hexad.restapikotlin.service

import de.hexad.restapikotlin.domain.User
import de.hexad.restapikotlin.domain.UserRequest
import de.hexad.restapikotlin.domain.UserResponse
import org.springframework.stereotype.Service

@Service
interface UserService {
    fun findUserById(userId :String) :User
    fun addUser(userRequest: UserRequest) :UserResponse
    fun login(userRequest: UserRequest) : UserResponse?
}