package com.example.lifestyleapplication.ui.workoutplans.interfaces

import com.example.lifestyleapplication.ui.workoutplans.model.allworkoutnumbermodel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecommendedNumberInterface {
    @GET("workoutplans/workoutnumber.php")
    fun getNumbers(
        @Query("day") day: String,
        @Query("section") section: String
    ): Call<allworkoutnumbermodel>
}