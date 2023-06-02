package de.hexad.restapikotlin.domain
import de.hexad.restapikotlin.domain.AppPermission.EMPLOYEE_READ
import de.hexad.restapikotlin.domain.AppPermission.EMPLOYEE_WRITE
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

/**
 * enum to
 * assign roles
 * to user
 */
enum class AppRole (private val permissions: MutableSet<AppPermission>) {
    USER(mutableSetOf(EMPLOYEE_READ)),
    ADMIN(mutableSetOf(EMPLOYEE_READ, EMPLOYEE_WRITE));

    private val getPermissions: MutableSet<AppPermission>
        get() = permissions

    val getGrantedAuthority: MutableSet<GrantedAuthority>
        get() {
           val grantedAuthorities: MutableSet<GrantedAuthority> = getPermissions
               .map { SimpleGrantedAuthority(it.userPermission) }.toMutableSet()
            grantedAuthorities.add(SimpleGrantedAuthority("ROLE_${this.name}"))

            return grantedAuthorities
        }

}