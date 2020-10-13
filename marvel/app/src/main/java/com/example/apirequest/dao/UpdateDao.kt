package com.example.apirequest.dao

class UpdateDao private constructor(val status: Status, val int: Int?, val error:String?) {
    companion object {
        fun success(int: Int) = UpdateDao(Status.SUCCESS,int, null)
        fun error(error: String) = UpdateDao(Status.ERROR, null, error)
    }
}