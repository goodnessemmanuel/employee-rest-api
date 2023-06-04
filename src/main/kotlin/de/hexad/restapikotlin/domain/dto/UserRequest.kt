package de.hexad.restapikotlin.domain.dto

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include
import de.hexad.restapikotlin.domain.AppRole
import java.io.Serializable
@JsonInclude(Include.NON_NULL)
data class UserRequest (
   val name: String?,
   val email: String,
   val password: String,
   val role: AppRole = AppRole.USER
) :Serializable