package de.hexad.restapikotlin.constant

object RequestURIConstant {
    const val API_BASE_URL = "/server/api"
    const val TOKEN_URI = "/auth/token"
    const val REGISTER = "/auth/register"
    const val LOGIN = "/auth/login"
    const val NG_LOCAL_URI = "http://localhost:4200"
    const val REACT_LOCAL_URI = "http://localhost:3000"
    const val HEALTH = "/v1/health"
    const val EMPLOYEES = "/v1/employee/list"
    const val ADD_EMPLOYEE = "/v1/employee/add"
    const val GET_EMPLOYEE = "/v1/employee/{id}"
}