package com.example.lifestyleapplication.ui.interfaces

import com.example.lifestyleapplication.ui.models.allMealDetails
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface SpecialMealInterface {
    @POST("mealplan/fasting.php")
    fun getData(
        @Query("mealplan") mealPlan: String,
        @Query("mealduration") mealDuration: String,
        @Query("day") day: String,
        @Query("condition") condition: String
    ): Call<allMealDetails>
}