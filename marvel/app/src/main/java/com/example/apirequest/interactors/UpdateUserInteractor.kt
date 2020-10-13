package com.example.apirequest.interactors

import com.example.apirequest.database.entities.UserEntity
import com.example.apirequest.failure.Failure
import com.example.apirequest.functional.Either
import com.example.apirequest.repositories.UserRepository

class UpdateUserInteractor(private val userRepository: UserRepository): UseCase<Int,UserEntity>() {
    override suspend fun run(params: UserEntity): Either<Failure, Int> = userRepository.updateUser(params)
}