package com.example.lifestyleapplication.ui.workoutplans.interfaces

import com.example.lifestyleapplication.ui.workoutplans.model.allworkoutmodel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SelectedRecommendedWorkOutNumberInterface {
    @GET("workoutplans/workout.php")
    fun getNumbers(
        @Query("day") day: String,
        @Query("number") number: String,
        @Query("section") section: String
    ): Call<allworkoutmodel>
}