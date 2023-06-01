package de.hexad.restapikotlin.domain

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(value = "api_user")
data class User(
    @Id
    val id: ObjectId? = null,
    val name: String,
    val email: String,
    val password: String,
)
