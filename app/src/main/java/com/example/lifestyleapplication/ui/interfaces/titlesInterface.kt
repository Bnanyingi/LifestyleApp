package com.example.lifestyleapplication.ui.interfaces

import com.example.lifestyleapplication.ui.models.titles
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface titlesInterface {
    @GET("author/{author}/title")
    fun getTitles(
        @Path("author") author: String
    ): Call<titles>
}