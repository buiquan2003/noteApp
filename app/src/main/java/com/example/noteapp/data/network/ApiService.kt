package com.example.noteapp.data.network

import com.example.noteapp.data.model.Note
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun getNote(@Url url: Url) : Response<List<Note>>

    @POST
    suspend fun insertNote(@Url url: Url): Note

    @PUT
    suspend fun updateNote(@Url url: Url): Note

    @DELETE
    suspend fun deleteNote(@Url url: Url): Note

    @PATCH
    suspend fun applyNote(@Url url: Url): Note

}