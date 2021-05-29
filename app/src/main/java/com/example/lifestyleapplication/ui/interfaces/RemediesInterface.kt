package com.example.lifestyleapplication.ui.interfaces

import com.example.lifestyleapplication.ui.models.allRemedies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RemediesInterface {
    @GET("remedies/remedylist.php")
    fun getData(
        @Query("illness") illness: String
    ): Call<allRemedies>
}