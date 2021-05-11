package com.example.lifestyleapplication.ui.interfaces

import com.example.lifestyleapplication.ui.models.author
import retrofit2.Call
import retrofit2.http.GET

interface authorInterface {
    @GET("author")
    fun getAuthors() : Call<author>
}