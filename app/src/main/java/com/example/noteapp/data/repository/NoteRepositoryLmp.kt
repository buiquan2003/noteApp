package com.example.noteapp.data.repository


import android.util.Log
import com.example.noteapp.data.model.Note
import com.example.noteapp.data.model.ResponseNote
import com.example.noteapp.domain.repository.NoteRepository
import com.example.noteapp.extensions.Resource
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject



class NoteRepositoryLmp @Inject constructor(
    private var database: DatabaseReference,

    ): NoteRepository {

    override fun insertNote(note: Note): Flow<Resource<Boolean>> = callbackFlow {
        val newNoteRef = database.child("Note").push() // Tạo một DatabaseReference mới và lấy ID được tạo bởi Firebase

        val newNoteId = newNoteRef.key
        if (newNoteId != null) {
            note.id = newNoteId

        newNoteRef.setValue(note)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    trySend(Resource.Success(true))
                } else {
                    trySend(Resource.Error(""))
                }
            }
            .addOnCanceledListener {

            }

        }

        awaitClose {
        }
    }



    override fun deleteNote(note: Note): Flow<Resource<Boolean>> {
        TODO("Not yet implemented")
    }

    override fun updateNote(note: Note): Flow<Resource<Boolean>> = callbackFlow {
        TODO("Not yet implemented")
    }

    override fun getNote(): Flow<List<Note>> = callbackFlow {
        val database = FirebaseDatabase.getInstance().getReference("Note")
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val noteList = mutableListOf<Note>()
                for (snapshot in snapshot.children) {
                    val note = snapshot.getValue(Note::class.java)
                    note?.let {
                        noteList.add(it)
                    }
                }
                Log.d("FirebaseData", "Received data from Firebase: $noteList")
                trySend(noteList)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        database.addValueEventListener(valueEventListener)

        awaitClose {
            database.removeEventListener(valueEventListener)
        }
    }


}


