package com.example.apirequest.repositories

import com.example.apirequest.database.dao.UserEntityDao
import com.example.apirequest.database.entities.UserEntity
import com.example.apirequest.failure.Failure
import com.example.apirequest.functional.Either

//Has every method from network and local
class UserRepository(private val userEntityDao: UserEntityDao) {

    fun logIn(set: Set<String>): Either<Failure, UserEntity>{
        return try{
           userEntityDao.logIn(set.first(), set.last())?.let {
                 Either.Right(it)
            } ?: Either.Left(Failure.DataBaseError("User not found"))
        }
        catch (ex: Throwable){
            Either.Left(Failure.DataBaseError(ex.localizedMessage))
        }
    }

    fun getUser(email: String): Either<Failure,UserEntity> {
        return try {
            userEntityDao.getUser(email)?.let {
                Either.Right(it)
            }?: Either.Left(Failure.DataBaseError("User not found"))
        }
        catch (ex: Throwable){
            Either.Left(Failure.DataBaseError(ex.localizedMessage?:"Get user error"))
        }
    }

    fun insertUser(userEntity: UserEntity) : Either<Failure, Long> {
        return try {
            Either.Right(userEntityDao.insertUser(userEntity))
        } catch (ex: Throwable){
            Either.Left(Failure.DataBaseError(ex.localizedMessage?:"Insert Error"))
        }
    }

    //Update complete element
    fun updateUser(userEntity: UserEntity): Either<Failure, Int>{
        return try{
            Either.Right(userEntityDao.updateUser(userEntity))
//            Either.Right(userEntityDao.updateUser(
//                userEntity.email,
//                userEntity.name,
//                userEntity.lastName,
//                userEntity.password,
//                userEntity.registerDate,
//                userEntity.lastLoginDate,
//                userEntity.profileImage
//                ))
        }catch(ex: Throwable){
            Either.Left(Failure.DataBaseError(ex.localizedMessage?:"Update Error"))
        }
    }

}