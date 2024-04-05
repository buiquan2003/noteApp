package com.example.noteapp.domain.uscase.usecaseUserAuth

import com.example.noteapp.data.model.Failure
import com.example.noteapp.extensions.Either
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

abstract class UseCase<in Param, out Result> {

    abstract suspend fun run(param: Param): Either<Failure, Result>

    operator fun invoke (
        param: Param,
        scope: CoroutineScope = CoroutineScope(Dispatchers.IO),
        onResult:(Either<Failure, Result>) -> Unit
    ) = scope.launch {
        val def = async { run(param) }
        onResult(def.await())
    }

    class None
}