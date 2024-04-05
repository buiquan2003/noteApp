package com.example.noteapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import com.example.noteapp.data.model.UserAuth

@Database(entities = [
    UserAuth::class
], version = 2)

abstract class AppDatabase: RoomDatabase() {

    abstract fun getAppDao(): AppDao

    companion object {
       const val DATABASE_NAME = "travel.db"
    }

}