package com.example.noteapp.extensions.connectInternet

import kotlinx.coroutines.flow.Flow

interface ConnectObserver {
    fun observer(): Flow<Status>

    enum class Status {
        Available, Unavailable, Losing, Lost
    }
}