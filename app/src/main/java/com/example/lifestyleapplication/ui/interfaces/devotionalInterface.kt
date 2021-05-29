package com.example.lifestyleapplication.ui.interfaces

import com.example.lifestyleapplication.ui.models.allDevotions
import com.example.lifestyleapplication.ui.models.devotionalTopicTitles
import com.example.lifestyleapplication.ui.models.devotions
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface devotionalInterface {
    @GET("devotions/devotion.php")
    fun getTitles(
        @Query("title") title: String
    ): Call<allDevotions>
}