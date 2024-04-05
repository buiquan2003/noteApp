package com.example.noteapp.presentation.di.module

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.training.traveling.presentation.di.annotation.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class FirebaseModule {

    @ApplicationScope
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @ApplicationScope
    @Provides
    fun provideFirebaseDatabase(): DatabaseReference = FirebaseDatabase.getInstance().reference
}