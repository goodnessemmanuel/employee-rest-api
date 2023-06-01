package de.hexad.restapikotlin.repository

import de.hexad.restapikotlin.domain.User
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository :MongoRepository<User, String>{
    fun findUserByEmail(email: String) : User?
}