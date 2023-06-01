package de.hexad.restapikotlin.domain

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include
import java.io.Serializable

@JsonInclude(Include.NON_NULL)
data class UserResponse (
    var id: String,
    var name: String,
    var email: String,
) :Serializable{
    constructor(): this("", "", "")
}