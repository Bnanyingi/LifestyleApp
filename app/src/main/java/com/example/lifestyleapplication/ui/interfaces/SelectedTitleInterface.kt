package com.example.lifestyleapplication.ui.interfaces

import com.example.lifestyleapplication.ui.models.poem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SelectedTitleInterface {
    @GET("author,title/{author};{title}")
    fun getSelectedTitle(
        @Path("author") author: String,
        @Path("title") title: String
    ): Call<List<poem>>
}