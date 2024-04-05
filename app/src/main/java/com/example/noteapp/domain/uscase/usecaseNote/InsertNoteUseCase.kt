package com.example.noteapp.domain.uscase.usecaseNote

import com.example.noteapp.data.model.Note
import com.example.noteapp.domain.repository.NoteRepository
import com.example.noteapp.extensions.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class InsertNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
){
     operator fun invoke(note: Note): Flow<Resource<Boolean>> = noteRepository.insertNote(note).flowOn(Dispatchers.IO)

}