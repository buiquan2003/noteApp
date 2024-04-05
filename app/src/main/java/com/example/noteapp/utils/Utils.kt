package com.example.noteapp.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.example.noteapp.utils.Utils.Permission.LEAST_PASSWORD_LENGTH

object Utils {
    fun formatDateTime(dateTime: String): String {
        return "\\d{4}-\\d{2}-\\d{2}".toRegex().find(dateTime)!!.value
    }

    fun checkPermission(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }

    fun validPassword(email: String, password: String, confirmPassword: String): Boolean {
        return password.length == confirmPassword.length
                && password.length >= LEAST_PASSWORD_LENGTH
                && email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()
    }

    object Permission {
        val arrayPermission = arrayOf(
            Manifest.permission.POST_NOTIFICATIONS,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        const val LEAST_PASSWORD_LENGTH = 6
    }

}