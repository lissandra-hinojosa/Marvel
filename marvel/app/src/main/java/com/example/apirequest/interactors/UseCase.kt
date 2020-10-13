package com.example.apirequest.interactors

import com.example.apirequest.failure.Failure
import com.example.apirequest.functional.Either
import kotlinx.coroutines.*

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Either<Failure, Type>

    operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit = {}): Job {
        val job = GlobalScope.async { run(params) }
        GlobalScope.launch(Dispatchers.Main) { onResult(job.await()) }
        return job
    }

    class None
}