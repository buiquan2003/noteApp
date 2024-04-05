package com.example.noteapp.extensions

abstract class Either<out L, out R> {

    data class Success<out R>(val valueR: R): Either<Nothing, R>()

    data class Failure<out L>(val valueL: L): Either<L, Nothing>()

    fun fold(funcL: ((L) -> Unit), funcR: ((R) -> Unit)): Any =
        when(this) {
            is Success -> funcR(valueR)
            is Failure -> funcL(valueL)
            else -> {}
        }

}