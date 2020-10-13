package com.example.apirequest.dao

import com.example.apirequest.database.entities.UserEntity

class UserEntityDao private constructor(val status: Status, val userEntity: UserEntity?, val error: String?){
    companion object {
        fun success(userEntity: UserEntity?) = UserEntityDao(Status.SUCCESS,userEntity,null)
        fun error(error: String?) = UserEntityDao(Status.ERROR,null,error)
    }
}