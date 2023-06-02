package de.hexad.restapikotlin.domain

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include
import java.io.Serializable
@JsonInclude(Include.NON_NULL)
data class UserRequest (
   val name: String?,
   val email: String,
   val password: String,
   val role: AppRole = AppRole.USER
) :Serializable