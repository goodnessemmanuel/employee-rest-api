package de.hexad.restapikotlin.service

import org.springframework.security.core.token.Token
import org.springframework.stereotype.Service

@Service
class TokenService(
//    private val jwtDecoder: JwtDecoder,
//    private val jwtEncoder: JwtEncoder,
) {
   /* private lateinit var userService: UserService
    fun createToken(user: User): String {
        val jwsHeader = JwsHeader.with { "HS256" }.build()
        val claims = JwtClaimsSet.builder()
            .issuedAt(Instant.now())
            .expiresAt(Instant.now().plus(30L, ChronoUnit.DAYS))
            .subject(user.name)
            .claim("userId", user.id)
            .build()
        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).tokenValue
    }

    fun parseToken(token: String): User? {
        return try {
            val jwt = jwtDecoder.decode(token)
            val userId = jwt.claims["userId"] as String
            userService.findUserById(userId)
        } catch (e: Exception) {
            null
        }
    }*/

     fun allocateToken(extendedInformation: String?): Token {
        TODO("Not yet implemented")
    }

     fun verifyToken(key: String?): Token {
        TODO("Not yet implemented")
    }
}