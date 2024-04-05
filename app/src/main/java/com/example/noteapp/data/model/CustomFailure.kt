package com.example.noteapp.data.model

sealed class Failure

data class CustomFailure(val e: Exception): Failure()