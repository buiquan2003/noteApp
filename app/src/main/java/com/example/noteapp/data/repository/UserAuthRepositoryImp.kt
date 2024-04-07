package com.example.noteapp.data.repository

import android.util.Log
import com.example.noteapp.R
import com.example.noteapp.data.local.database.AppDao
import com.example.noteapp.data.model.UserAuth
import com.example.noteapp.data.network.ApiService
import com.example.noteapp.domain.repository.UserAuthRepository
import com.example.noteapp.extensions.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.lang.IllegalArgumentException
import javax.inject.Inject

class UserAuthRepositoryImp @Inject constructor(
    private val apiService: ApiService,
    private val firebaseAuth: FirebaseAuth,
    private val appDao: AppDao

): UserAuthRepository {

    override fun signUp(userAuth: UserAuth): Flow<Resource<Boolean>> = flow{
        firebaseAuth.createUserWithEmailAndPassword(userAuth.email, userAuth.password).await()
        emit(Resource.Success(true))
    }

    override fun signIn(userAuth: UserAuth): Flow<Resource<Boolean>> = callbackFlow {
        try {
            if (userAuth.email.isNotEmpty() && userAuth.password.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(userAuth.email, userAuth.password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            trySend(Resource.Success(true))
                        } else {
                            val exception = task.exception
                            if (exception is FirebaseAuthInvalidCredentialsException) {
                                trySend(Resource.Error("${R.string.Username_password_incorrect}"))
                            } else {
                                trySend(Resource.Error("${exception?.message}"))
                            }
                        }
                    }
            }
        } catch (e: Exception) {
            Log.d("Error", "Failed: $e")
            trySend(Resource.Error("Failed: ${e.message}"))
        }
        awaitClose()
    }

    override fun signOut(): Flow<Resource<Boolean>> = flow {
      firebaseAuth.signOut()
        emit(Resource.Success(true))
    }

    override fun getUserFromDB(): Flow<List<UserAuth>>  = flow {
        emit(appDao.getUser())
    }

    override suspend fun insertToDB(userAuth: UserAuth) {
        appDao.insertUser(userAuth)
    }


}