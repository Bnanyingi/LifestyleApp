package com.example.lifestyleapplication.ui.interfaces

import com.example.lifestyleapplication.ui.models.allMoodVerses
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoodVersesInterface {
    @GET("bibleverses/verse.php")
    fun getVerse(
        @Query("mood") mood: String
    ): Call<allMoodVerses>
}