package com.example.noteapp.presentation.ui.activity

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.local.pref.PreferenceHelper
import com.example.noteapp.data.model.Note
import com.example.noteapp.data.model.UserAuth
import com.example.noteapp.domain.uscase.usecaseNote.GetNoteUseCase
import com.example.noteapp.domain.uscase.usecaseUserAuth.GetUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val getUseCase: GetUserUseCase,
        private val getNoteUseCase: GetNoteUseCase
): ViewModel() {

    val showBottomBar = MutableStateFlow(true)
    val userExisted = MutableStateFlow(false)
    private var _listNote: MutableStateFlow<List<Note>> = MutableStateFlow(emptyList())

    val listNote: StateFlow<List<Note>> get() = _listNote

    init {
        getUserFromDB()
    }

    private fun getUserFromDB() {
        viewModelScope.launch {
            getUseCase().collect { list ->
                userExisted.value = list.contains(
                    UserAuth(
                        PreferenceHelper.getInstance().userEmail,
                        PreferenceHelper.getInstance().password,
                        "",
                        "",
                        ""
                    )
                )
            }
        }
    }


//    fun getDataNote() {
//        viewModelScope.launch {
//            getNoteUseCase().collect { result ->
//              _listNote.value = result
//            }
//        }
//    }

    fun getDataNote() {
        viewModelScope.launch {
            if (getNoteUseCase != null) {
                getNoteUseCase().collect { result ->
                    Log.d("getDataNote", "Received note list: ${result.size}")
                    _listNote.value = result
                }
            } else {
                Log.e("getDataNote", "getNoteUseCase is null")
            }
        }
    }
}


