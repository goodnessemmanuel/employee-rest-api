package de.hexad.restapikotlin.service.impl

import de.hexad.restapikotlin.constant.UtilConstant.AUTHENTICATION_REQUIRED
import de.hexad.restapikotlin.constant.UtilConstant.INVALID_CREDENTIAL
import de.hexad.restapikotlin.constant.UtilConstant.USER_NOT_FOUND
import de.hexad.restapikotlin.domain.User
import de.hexad.restapikotlin.domain.dto.TokenResponse
import de.hexad.restapikotlin.domain.dto.UserRequest
import de.hexad.restapikotlin.domain.dto.UserResponse
import de.hexad.restapikotlin.exception.InvalidAuthenticationException
import de.hexad.restapikotlin.exception.UserNotFoundException
import de.hexad.restapikotlin.repository.UserRepository
import de.hexad.restapikotlin.service.UserService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.security.oauth2.jwt.JwtException
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.stream.Collectors

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val jwtEncoder: JwtEncoder
) :UserService, UserDetailsService {
    private val logger = LoggerFactory.getLogger(this.javaClass.name)

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

    override fun createTokenToAccessApi(authentication: Authentication?): ResponseEntity<Any> {
        if (authentication == null)
            return ResponseEntity.badRequest().body(AUTHENTICATION_REQUIRED)

        return try {

            val authenticatedTokenValue: String = createJwtFromBasicAuthentication(authentication)

            ResponseEntity.ok()
                .body(TokenResponse( (authentication.principal as User).username, authenticatedTokenValue))

        } catch (ex: JwtException){
            ResponseEntity.internalServerError().body(ex.message)
        }
    }

    override fun loadUserByUsername(email: String?): UserDetails {
        return email?.let { userRepository.findUserByEmail(it) }
            ?: throw UserNotFoundException("$INVALID_CREDENTIAL: $email")
    }

    private fun createJwtFromBasicAuthentication(authentication: Authentication) :String{
        logger.info(">>>> generating token for authenticated user of: $authentication")
        val now: Instant = Instant.now()
        val scope:String = authentication.authorities.stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(" "))

        val claims : JwtClaimsSet = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(now)
            .expiresAt(now.plusSeconds(3600 * 2))
            .subject(authentication.name)
            .claim("scope", scope)
            .build()

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).tokenValue
    }
}