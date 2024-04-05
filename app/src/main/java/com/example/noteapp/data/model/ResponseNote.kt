package com.example.noteapp.data.model

import com.google.gson.annotations.SerializedName

data class ResponseNote(
    val notes: List<Note>
){
    constructor() : this(
        emptyList()
    )
}
