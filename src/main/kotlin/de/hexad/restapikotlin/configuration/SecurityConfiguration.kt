package de.hexad.restapikotlin.configuration

import de.hexad.restapikotlin.constant.RequestURIConstant.NG_LOCAL_URI
import de.hexad.restapikotlin.constant.RequestURIConstant.REACT_LOCAL_URI
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.security.KeyPair
import java.security.KeyPairGenerator

@Configuration
//@EnableWebSecurity
class SecurityConfiguration () {
    private val logger = LoggerFactory.getLogger(this.javaClass.name)
    /*@Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain{
        with(http){
            authorizeHttpRequests { it
                .requestMatchers(HEALTH, REGISTER)
                    .permitAll()
                    .anyRequest()
                    .authenticated()
            }
           *//* csrf { it.ignoringRequestMatchers(TOKEN_URI) }
            httpBasic(Customizer.withDefaults())*//*
           // oauth2ResourceServer() .jwt()
            *//*authenticationManager { auth ->
                   run {
                        val jwt = auth as BearerAccessToken
                       //val jwt1 = jwtDecoder.decode(jwt.value)
                       val jwt1 = jwtDecoder(keyPair()).decode(jwt.value)
                       val userId = jwt1.claims["userId"] as Long
                        val user = userService.findById(userId) ?: throw Exception("Invalid token")
                      UsernamePasswordAuthenticationToken(user, "", listOf(SimpleGrantedAuthority("USER")))
                    }
            }*//*
                //.
        }
       return http.build()
    }*/

    /*@Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain{
        with(http){
                            authorizeHttpRequests { it
                                .requestMatchers(HEALTH)
                                    .permitAll()
                                    .anyRequest()
                                    .authenticated()
                            }
                            csrf { it.ignoringRequestMatchers(TOKEN_URI) }
                            httpBasic(Customizer.withDefaults())
                            oauth2ResourceServer{it.jwt()}
                            sessionManagement { session ->
                                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                            }
                            exceptionHandling ()
                        }
                       return http.build()
                    }
*/
    /*@Bean
    fun jwtDecoder(keyPair: KeyPair) :JwtDecoder{
        logger.debug("decoding token with pub key {}", keyPair.public)
        return NimbusJwtDecoder.withPublicKey(keyPair.public as RSAPublicKey).build()
    }

    @Bean
    fun jwtEncoder(keyPair: KeyPair) :JwtEncoder{
        val jwk = RSAKey.Builder(keyPair.public as RSAPublicKey)
            .privateKey(keyPair.private)
            .keyID(UUID.randomUUID().toString())
            .build()
        logger.debug("creating json web key source for encoding {}", jwk)
        return NimbusJwtEncoder(ImmutableJWKSet(JWKSet(jwk)))
    }
*/

    @Bean
    fun keyPair() :KeyPair {
        val keyPairGen = KeyPairGenerator.getInstance("RSA")
        keyPairGen.initialize(2048)
        return keyPairGen.genKeyPair()
    }

    /*@Bean
    fun bearerTokenResolver(): BearerTokenResolver {
        val bearerTokenResolver = DefaultBearerTokenResolver()
        bearerTokenResolver.setBearerTokenHeaderName(HttpHeaders.PROXY_AUTHORIZATION)
        return bearerTokenResolver
    }*/

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

}