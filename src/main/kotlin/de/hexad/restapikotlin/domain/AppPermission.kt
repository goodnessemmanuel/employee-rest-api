package de.hexad.restapikotlin.domain

enum class AppPermission(private val permission: String){
    EMPLOYEE_READ("employee:read"),
    EMPLOYEE_WRITE("employee:write");

    val userPermission
        get() = permission
}