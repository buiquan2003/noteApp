package com.example.noteapp.domain.repository

import com.example.noteapp.data.model.Note
import com.example.noteapp.data.model.ResponseNote
import com.example.noteapp.extensions.Resource
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

     fun insertNote(note: Note): Flow<Resource<Boolean>>

     fun deleteNote(note: Note): Flow<Resource<Boolean>>

     fun updateNote(note: Note): Flow<Resource<Boolean>>

     fun getNote(): Flow<List<Note>>
}