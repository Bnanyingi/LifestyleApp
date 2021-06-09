package com.example.lifestyleapplication.ui.interfaces

import com.example.lifestyleapplication.ui.models.AllSelectedDays
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface IntermittentDurationInterface {
    @POST("mealplan/days.php")
    fun getDuration(
        @Query("day") day: String
    ): Call<AllSelectedDays>
}