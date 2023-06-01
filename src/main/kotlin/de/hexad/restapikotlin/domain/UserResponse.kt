package de.hexad.restapikotlin.domain

import java.io.Serializable


data class UserResponse (
    val id: String,
    val name: String,
    val email: String,
) :Serializable