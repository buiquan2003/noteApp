package com.example.noteapp.domain.uscase.usecaseNote

import android.util.Log
import com.example.noteapp.data.model.Note
import com.example.noteapp.data.model.ResponseNote
import com.example.noteapp.domain.repository.NoteRepository
import com.example.noteapp.extensions.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(): Flow<List<Note>> {
        return noteRepository.getNote()
            .flowOn(Dispatchers.IO)
            .also { flow ->
                flow.collect { noteList ->
                    if (noteList.isEmpty()) {
                        Log.d("GetNoteUseCase", "No notes received")
                    } else {
                        Log.d("GetNoteUseCase", "Received note list: $noteList")
                    }
                }
            }
    }
}