package com.example.lifestyleapplication.ui.interfaces

import com.example.lifestyleapplication.ui.models.allDays
import retrofit2.Call
import retrofit2.http.GET

interface DaysInterface {
    @GET("mealplan/selectedday.php")
    fun getDays(): Call<allDays>
}