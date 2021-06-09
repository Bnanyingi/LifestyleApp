package com.example.lifestyleapplication.ui.interfaces

import com.example.lifestyleapplication.ui.models.allMealDetails
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface CustomisedViewMealInterface {
    @POST("mealplan/recommended.php")
    fun getData(
        @Query("mealplan") mealPlan: String,
        @Query("mealduration") mealDuration: String,
        @Query("day") day: String
    ): Call<allMealDetails>
}