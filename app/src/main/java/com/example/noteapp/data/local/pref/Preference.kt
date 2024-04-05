package com.example.noteapp.data.local.pref

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.noteapp.R
import kotlin.reflect.KProperty

abstract class Preference(val context: Context) {

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(context.getString(R.string.preference_file_key), Context.MODE_PRIVATE)
    }

    enum class Type {
        String, Float, Integer, Boolean, Long
    }

    interface PrefDelegate<T> {
        operator fun getValue(thisRef: Any, property: KProperty<*>): T

        operator fun setValue(thisRef: Any, property: KProperty<*>, value: T)
    }

    inner class GenericPrefDelegate<T>(
        private val prefKey: String,
        private val defaultValue: T,
        val type: Type
    ): PrefDelegate<T> {
        override fun getValue(thisRef: Any, property: KProperty<*>): T {
            return when (type) {
                Type.String -> { sharedPreferences.getString(prefKey, defaultValue as String) as T }
                Type.Float -> { sharedPreferences.getFloat(prefKey, defaultValue as Float) as T }
                Type.Integer -> { sharedPreferences.getInt(prefKey, defaultValue as Int) as T }
                Type.Boolean -> { sharedPreferences.getBoolean(prefKey, defaultValue as Boolean) as T }
                else -> { sharedPreferences.getLong(prefKey, defaultValue as Long) as T }
            }
        }

        override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
            when (type) {
                Type.String -> {
                    sharedPreferences.edit(true) { putString(prefKey, value as String) }
                }

                Type.Integer -> {
                    sharedPreferences.edit(true) { putInt(prefKey, value as Int) }
                }

                Type.Float -> {
                    sharedPreferences.edit(true) { putFloat(prefKey, value as Float) }
                }

                Type.Boolean -> {
                    sharedPreferences.edit(true) { putBoolean(prefKey, value as Boolean) }
                }

                else -> {
                    sharedPreferences.edit(true) { putLong(prefKey, value as Long) }
                }
            }
        }
    }

    fun stringPref(
        prefKey: String,
        defaultValue: String
    ): GenericPrefDelegate<String> = GenericPrefDelegate(prefKey, defaultValue, Type.String)

    fun floatPref(
        prefKey: String,
        defaultValue: Float
    ): GenericPrefDelegate<Float> = GenericPrefDelegate(prefKey, defaultValue, Type.Float)

    fun intPref(
        prefKey: String,
        defaultValue: Int
    ): GenericPrefDelegate<Int> = GenericPrefDelegate(prefKey, defaultValue, Type.Integer)

    fun booleanPref(
        prefKey: String,
        defaultValue: Boolean
    ): GenericPrefDelegate<Boolean> = GenericPrefDelegate(prefKey, defaultValue, Type.Boolean)

    fun longPref(
        prefKey: String,
        defaultValue: Long
    ): GenericPrefDelegate<Long> = GenericPrefDelegate(prefKey, defaultValue, Type.Long)
}