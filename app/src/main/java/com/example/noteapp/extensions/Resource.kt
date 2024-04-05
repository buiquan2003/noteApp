package com.example.noteapp.extensions

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Loading<T>: Resource<T>()

    class Success<T>(val value: T): Resource<T>(data = value)

    class Error<T>(errorMessage: String): Resource<T>(message = errorMessage)
}