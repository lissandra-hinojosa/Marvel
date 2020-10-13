package com.example.apirequest.failure

sealed class Failure(val message: String) {
    class NetworkConnection(message: String) : Failure(message)
    class ServerError(message: String) : Failure(message)
    class DataBaseError(message: String) : Failure(message)
}