package com.example.noteapp.presentation.di.module

import com.example.noteapp.data.local.database.AppDao
import com.example.noteapp.data.network.ApiService
import com.example.noteapp.data.repository.NoteRepositoryLmp
import com.example.noteapp.data.repository.UserAuthRepositoryImp
import com.example.noteapp.domain.repository.NoteRepository
import com.example.noteapp.domain.repository.UserAuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.training.traveling.presentation.di.annotation.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @ApplicationScope
    @Provides
    fun providesAuthRepository(
        apiService: ApiService,
        firebaseAuth: FirebaseAuth,
        appDao: AppDao,
    ):UserAuthRepository = UserAuthRepositoryImp(apiService, firebaseAuth, appDao)

    @ApplicationScope
    @Provides
    fun noteRepository(
        database: DatabaseReference,
    ):NoteRepository = NoteRepositoryLmp(database)
}