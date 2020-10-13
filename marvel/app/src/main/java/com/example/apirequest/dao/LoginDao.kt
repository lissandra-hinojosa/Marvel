package com.example.apirequest.dao

import com.example.apirequest.database.entities.UserEntity
import java.lang.reflect.Constructor

class LoginDao private constructor(val status: Status, val userEntity: UserEntity?, val error:String?) {
    companion object {
        fun success(userEntity: UserEntity) = LoginDao(Status.SUCCESS, userEntity, null)
        fun error(error: String) = LoginDao(Status.ERROR,null,error)
    }
}