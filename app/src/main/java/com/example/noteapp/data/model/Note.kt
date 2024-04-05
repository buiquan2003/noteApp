package com.example.noteapp.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(

    @SerializedName("id")
    var id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("content")
    val content: String,

    @SerializedName("time")
    val time: String,

    @SerializedName("date")
    val date: String
): Parcelable {
    constructor() : this("", "", "", "", "")

}
