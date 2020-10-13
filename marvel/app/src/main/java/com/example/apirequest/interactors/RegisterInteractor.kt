package com.example.apirequest.interactors

import com.example.apirequest.database.entities.UserEntity
import com.example.apirequest.failure.Failure
import com.example.apirequest.functional.Either
import com.example.apirequest.repositories.UserRepository

class RegisterInteractor(private val userRepository: UserRepository): UseCase<Long, UserEntity>() {
    override suspend fun run(params: UserEntity) = userRepository.insertUser(params)
}