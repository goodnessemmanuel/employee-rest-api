package de.hexad.restapikotlin.service.impl

import de.hexad.restapikotlin.domain.User
import de.hexad.restapikotlin.domain.UserRequest
import de.hexad.restapikotlin.domain.UserResponse
import de.hexad.restapikotlin.exception.InvalidAuthenticationException
import de.hexad.restapikotlin.exception.UserNotFoundException
import de.hexad.restapikotlin.repository.UserRepository
import de.hexad.restapikotlin.service.UserService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl (
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) :UserService {

    override fun findUserById(userId: String): User {
       return userRepository.findById(userId)
           .orElseThrow { UserNotFoundException("user not found! id: $userId") }
    }

    override fun addUser(userRequest: UserRequest): UserResponse {
        val newUser = userRepository.save(
            User(
                name = userRequest.name?:"",
                email = userRequest.email,
                password = passwordEncoder.encode(userRequest.password)
            )
        )
        return UserResponse(newUser.id.toString(), newUser.name, newUser.email)
    }

    override fun login(userRequest: UserRequest): UserResponse? {
        val user = userRepository.findUserByEmail(userRequest.email) ?: throw InvalidAuthenticationException("invalid login credentials")
        val userResponse = UserResponse()
        userResponse.email = user.email
        userResponse.id = user.id?.toString()?:""
        userResponse.name = user.name
        return userResponse
    }
}