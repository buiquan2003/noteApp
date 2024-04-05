package com.example.noteapp.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.noteapp.data.model.UserAuth

@Dao
interface AppDao {
    @Insert
    fun insertUser(userAuth: UserAuth) :Long

    @Query("SELECT * FROM user")
    fun getUser(): List<UserAuth>

}