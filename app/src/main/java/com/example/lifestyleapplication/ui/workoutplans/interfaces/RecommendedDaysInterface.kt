package com.example.lifestyleapplication.ui.workoutplans.interfaces

import com.example.lifestyleapplication.ui.workoutplans.model.allplandaysmodel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecommendedDaysInterface {
    @GET("workoutplans/workoutdays.php")
    fun getDays(
        @Query("days") days: String
    ): Call<allplandaysmodel>
}