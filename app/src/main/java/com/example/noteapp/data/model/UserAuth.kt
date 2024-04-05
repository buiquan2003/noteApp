package com.example.noteapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserAuth(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("email")
    val email: String,
    @ColumnInfo("password")
    val password: String,
    @ColumnInfo("birthday")
    val birthday: String,
    @ColumnInfo("phone")
    val phone: String,
    @ColumnInfo("avatar")
    val avatar: String
)