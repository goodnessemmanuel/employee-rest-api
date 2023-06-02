package de.hexad.restapikotlin.service.impl

import de.hexad.restapikotlin.constant.ErrorMessage.INVALID_CREDENTIAL
import de.hexad.restapikotlin.constant.ErrorMessage.USER_NOT_FOUND
import de.hexad.restapikotlin.domain.User
import de.hexad.restapikotlin.domain.UserRequest
import de.hexad.restapikotlin.domain.UserResponse
import de.hexad.restapikotlin.exception.InvalidAuthenticationException
import de.hexad.restapikotlin.exception.UserNotFoundException
import de.hexad.restapikotlin.repository.UserRepository
import de.hexad.restapikotlin.service.UserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl (
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) :UserService, UserDetailsService {

    override fun findUserById(userId: String): User {
       return userRepository.findById(userId)
           .orElseThrow { UserNotFoundException("$USER_NOT_FOUND: $userId") }
    }

    override fun addUser(userRequest: UserRequest): UserResponse {
        val newUser = userRepository.save(
            User(
                name = userRequest.name?:"",
                email = userRequest.email,
                password = passwordEncoder.encode(userRequest.password),
                grantedAuthorities = userRequest.role.getGrantedAuthority
            )
        )
        return UserResponse(newUser.id.toString(), newUser.name, newUser.email)
    }

    override fun login(userRequest: UserRequest): UserResponse? {
        try {
            val user = loadUserByUsername(userRequest.email) as User
            if (!passwordEncoder.matches(userRequest.password, user.password) )
                throw InvalidAuthenticationException(INVALID_CREDENTIAL)

            val userResponse = UserResponse()
            userResponse.email = user.email
            userResponse.id = user.id?.toString()?:""
            userResponse.name = user.name
            return userResponse
        } catch (ex: UserNotFoundException) {
            throw InvalidAuthenticationException(ex.message?:ex.stackTraceToString())
        }
    }

    override fun loadUserByUsername(email: String?): UserDetails {
        return email?.let { userRepository.findUserByEmail(it) }
            ?: throw UserNotFoundException("$INVALID_CREDENTIAL: $email")
    }
}