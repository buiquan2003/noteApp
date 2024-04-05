package com.example.noteapp.presentation.ui.fragment.addNote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp.data.model.Note
import com.example.noteapp.domain.uscase.usecaseNote.DeleteNoteUseCase
import com.example.noteapp.domain.uscase.usecaseNote.GetNoteUseCase
import com.example.noteapp.domain.uscase.usecaseNote.InsertNoteUseCase
import com.example.noteapp.domain.uscase.usecaseNote.UpdateNoteUseCase
import com.example.noteapp.extensions.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddViewModel @Inject constructor(
    private val insertNoteCase: InsertNoteUseCase,
    private val updateNoteCase: UpdateNoteUseCase,
    private val deleteNoteCase: DeleteNoteUseCase,
    private val getNoteCase: GetNoteUseCase,
): ViewModel() {
    val addBack = MutableStateFlow(false)
    val insertSuccess = MutableStateFlow(false)

    private val _showTextInCorrect = MutableStateFlow(false)

    fun insertNote(note: Note) {
        if (note.title.isNotEmpty() || note.content.isNotEmpty() ) {
           viewModelScope.launch {
               insertFirebase(note)
           }
        }
    }

    private fun insertFirebase(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            insertNoteCase(note).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        insertSuccess.value = true

                    } else -> _showTextInCorrect.value = true
                }
            }
        }
    }

    fun updateNote(note: Note) {

    }

    fun deleteNote(note: Note) {

    }

}