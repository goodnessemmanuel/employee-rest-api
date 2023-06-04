package de.hexad.restapikotlin.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Document(value = "api_user")
 data class User(
    @Id
    val id: ObjectId? = null,
    val name: String,
    val email: String,
    private val password: String,
    private val isAccountNonExpired:Boolean = true,
    private val isAccountNonLocked:Boolean = true,
    private val isCredentialsNonExpired:Boolean = true,
    private val isEnabled:Boolean = true,
    private val grantedAuthorities: MutableSet<GrantedAuthority> = mutableSetOf()
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = grantedAuthorities

    override fun getPassword(): String = password

    override fun getUsername(): String = email

    override fun isAccountNonExpired(): Boolean = isAccountNonExpired

    override fun isAccountNonLocked():Boolean = isAccountNonLocked

    override fun isCredentialsNonExpired(): Boolean = isCredentialsNonExpired

    override fun isEnabled(): Boolean = isEnabled
}
