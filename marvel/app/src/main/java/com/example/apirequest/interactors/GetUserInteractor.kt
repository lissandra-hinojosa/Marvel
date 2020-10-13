package com.example.apirequest.interactors

import com.example.apirequest.database.entities.UserEntity
import com.example.apirequest.failure.Failure
import com.example.apirequest.functional.Either
import com.example.apirequest.repositories.UserRepository

class GetUserInteractor(private val userRepository: UserRepository): UseCase<UserEntity,String>(){
    override suspend fun run(params: String): Either<Failure, UserEntity> = userRepository.getUser(params)
}