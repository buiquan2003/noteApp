package com.example.noteapp.domain.repository

import com.example.noteapp.data.model.UserAuth
import com.example.noteapp.extensions.Resource
import kotlinx.coroutines.flow.Flow

interface UserAuthRepository {

    fun signUp(userAuth: UserAuth): Flow<Resource<Boolean>>

    fun signIn(userAuth: UserAuth): Flow<Resource<Boolean>>

    fun signOut(): Flow<Resource<Boolean>>

    fun getUserFromDB(): Flow<List<UserAuth>>

    suspend fun insertToDB(userAuth: UserAuth)

}