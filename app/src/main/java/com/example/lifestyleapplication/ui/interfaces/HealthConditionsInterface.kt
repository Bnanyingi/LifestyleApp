package com.example.lifestyleapplication.ui.interfaces

import com.example.lifestyleapplication.ui.models.AllHealthConditions
import retrofit2.Call
import retrofit2.http.GET

interface HealthConditionsInterface {
    @GET("mealplan/healthconditions.php")
    fun getConditions(): Call<AllHealthConditions>
}