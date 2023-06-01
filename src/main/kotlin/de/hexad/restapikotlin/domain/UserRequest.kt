package de.hexad.restapikotlin.domain

import java.io.Serializable

data class UserRequest (
    val name: String?,
    val email: String,
    val password: String,
) :Serializable