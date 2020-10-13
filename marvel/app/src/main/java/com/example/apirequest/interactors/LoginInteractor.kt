package com.example.apirequest.interactors

import com.example.apirequest.database.entities.UserEntity
import com.example.apirequest.failure.Failure
import com.example.apirequest.functional.Either
import com.example.apirequest.repositories.UserRepository

class LoginInteractor(private val userRepository: UserRepository): UseCase<UserEntity,Set<String>>() {
    override suspend fun run(params: Set<String>): Either<Failure, UserEntity> = userRepository.logIn(params)
}