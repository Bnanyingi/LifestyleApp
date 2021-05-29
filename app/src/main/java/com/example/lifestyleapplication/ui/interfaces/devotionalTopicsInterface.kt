package com.example.lifestyleapplication.ui.interfaces

import com.example.lifestyleapplication.ui.models.allDevotionalTopics
import com.example.lifestyleapplication.ui.models.devotionalTopics
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface devotionalTopicsInterface {
    @GET("devotions/topics.php")
    fun getTopics(): Call<allDevotionalTopics>
}