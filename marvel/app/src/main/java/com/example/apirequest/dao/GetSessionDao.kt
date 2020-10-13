package com.example.apirequest.dao

import com.example.apirequest.database.entities.UserEntity

class GetSessionDao private constructor(val status: Status, val userEntity: UserEntity?, val error: String?){
    companion object {
        fun success(userEntity: UserEntity) = GetSessionDao(Status.SUCCESS,userEntity,null)
        fun error(error: String?) = GetSessionDao(Status.ERROR,null,error)
    }
}