package com.example.noteapp.data.model

sealed class ErrorType {

    object ErrorToken: ErrorType()

    object ErrorSign: ErrorType()
}