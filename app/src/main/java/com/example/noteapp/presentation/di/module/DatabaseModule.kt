package com.example.noteapp.presentation.di.module

import android.app.Application
import androidx.room.Room
import com.example.noteapp.data.local.database.AppDao
import com.example.noteapp.data.local.database.AppDatabase
import com.example.noteapp.data.local.database.AppDatabase.Companion.DATABASE_NAME
import com.training.traveling.presentation.di.annotation.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    // phương thức module Dagger tạo và cấu hình một phiên bản của
    // AppDatabase bằng cách sử dụng Room.
    // Chú thích @ApplicationScope đảm bảo rằng phiên bản
    // này sẽ có sẵn trong suốt vòng đời của ứng dụng, và
    @ApplicationScope
    @Provides
    fun providerDatabase(application: Application): AppDatabase = Room.databaseBuilder(
        application, AppDatabase::class.java, DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    @ApplicationScope
    @Provides
    fun provideAppDao(appDatabase: AppDatabase): AppDao = appDatabase.getAppDao()

}