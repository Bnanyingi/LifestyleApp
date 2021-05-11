package com.example.lifestyleapplication.ui.interfaces

import com.example.lifestyleapplication.ui.models.allQuotes
import retrofit2.Call
import retrofit2.http.GET

interface quotesInterface {
    @GET("qotd")
    fun getQuotes(): Call<allQuotes>
}