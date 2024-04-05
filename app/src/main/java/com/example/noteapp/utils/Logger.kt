package com.example.noteapp.utils

import com.google.firebase.BuildConfig
import timber.log.Timber

object Logger {
    private const val TAG = "Traveling"

    fun d(s: String, vararg objects: Any?) {
        Timber.tag(TAG).d(s, objects)
    }

    fun d(throwable: Throwable, s: String, vararg objects: Any?) {
        Timber.tag(TAG).d(throwable, s, objects)
    }

    fun i(s: String, vararg objects: Any?) {
        Timber.tag(TAG).i(s, objects)
    }

    fun i(throwable: Throwable, s: String, vararg objects: Any?) {
        Timber.tag(TAG).i(throwable, s, objects)
    }

    fun e(s: String, vararg objects: Any?) {
        Timber.tag(TAG).e(s, objects)
    }

    fun e(throwable: Throwable, s: String, vararg objects: Any?) {
        Timber.tag(TAG).e(throwable, s, objects)
    }

    fun init() {
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}