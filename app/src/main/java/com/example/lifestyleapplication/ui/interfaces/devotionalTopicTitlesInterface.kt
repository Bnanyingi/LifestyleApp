package com.example.lifestyleapplication.ui.interfaces

import com.example.lifestyleapplication.ui.models.allDevotionalTopicTitles
import com.example.lifestyleapplication.ui.models.devotionalTopicTitles
import com.example.lifestyleapplication.ui.models.devotionalTopics
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface devotionalTopicTitlesInterface {
    @GET("devotions/topic_title.php")
    fun getTitles(
        @Query("topic") topic: String
    ): Call<allDevotionalTopicTitles>
}