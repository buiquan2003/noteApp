package com.example.noteapp.data.local.pref

import android.content.Context
import com.example.noteapp.NoteActivity
import javax.inject.Inject

class PreferenceHelper @Inject constructor(
    context: Context
): Preference(
    context
) {
    var userEmail by stringPref("user_email", "")
    var password by stringPref("password", "")

    companion object {
        private var mInstance: PreferenceHelper? = null

        fun getInstance(): PreferenceHelper {
            if (mInstance == null) {
                mInstance = PreferenceHelper(NoteActivity.instance!!)
            }
            return mInstance!!
        }
    }
}