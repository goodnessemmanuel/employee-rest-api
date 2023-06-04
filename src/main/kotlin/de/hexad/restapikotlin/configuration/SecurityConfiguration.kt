package de.hexad.restapikotlin.configuration

import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import de.hexad.restapikotlin.constant.RequestURIConstant.HEALTH
import de.hexad.restapikotlin.constant.RequestURIConstant.NG_LOCAL_URI
import de.hexad.restapikotlin.constant.RequestURIConstant.REACT_LOCAL_URI
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.interfaces.RSAPublicKey
import java.util.*

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfiguration{
    private val logger = LoggerFactory.getLogger(this.javaClass.name)
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

            with(http) {
                csrf { it.disable() }
                authorizeHttpRequests { it
                    .requestMatchers(HttpMethod.GET, HEALTH).permitAll()
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                        .anyRequest()
                        .authenticated()
                }
                httpBasic(Customizer.withDefaults())
                oauth2ResourceServer().jwt()
                sessionManagement { session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                }
                exceptionHandling { exceptions -> exceptions
                    .authenticationEntryPoint(BearerTokenAuthenticationEntryPoint())
                    .accessDeniedHandler(BearerTokenAccessDeniedHandler())}

            }
       return http.build()
    }
    @Bean
    fun jwtDecoder(keyPair: KeyPair) : JwtDecoder {
        logger.debug("decoding token with pub key {}", keyPair.public)
        return NimbusJwtDecoder.withPublicKey(keyPair.public as RSAPublicKey).build()
    }

    @Bean
    fun jwtEncoder(keyPair: KeyPair) : JwtEncoder {
        val jwk = RSAKey.Builder(keyPair.public as RSAPublicKey)
            .privateKey(keyPair.private)
            .keyID(UUID.randomUUID().toString())
            .build()
        logger.debug("creating json web key source for encoding {}", jwk)
        return NimbusJwtEncoder(ImmutableJWKSet(JWKSet(jwk)))
    }

    @Bean
    fun keyPair() :KeyPair {
        val keyPairGen = KeyPairGenerator.getInstance("RSA")
        keyPairGen.initialize(2048)
        return keyPairGen.genKeyPair()
    }

    @Bean
    fun passwordEncoder() :BCryptPasswordEncoder{
        return BCryptPasswordEncoder()
    }

    /**
     * allow resource
     * sharing for any
     * app running on
     * local URL
     */
    @Bean
    fun corsConfigurer() : WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry
                    .addMapping("*")
                    .allowedOrigins(NG_LOCAL_URI, REACT_LOCAL_URI)
            }
        }
    }

    @Bean
    fun authenticationManager(authConfiguration: AuthenticationConfiguration): AuthenticationManager{
        return authConfiguration.authenticationManager
    }

}