package com.example.apirequest.dao

class RegisterDao private constructor(val status: Status, val identifier: Long?, val error: String?) {

    companion object {
        fun success(identifier: Long) = RegisterDao(Status.SUCCESS, identifier, null)
        fun error(error: String) = RegisterDao(Status.ERROR, null, error)

    }
}