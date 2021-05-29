package com.example.lifestyleapplication.ui.interfaces

import com.example.lifestyleapplication.ui.models.allIllness
import retrofit2.Call
import retrofit2.http.GET

interface IllnessInterface {
    @GET("remedies/illnesslist.php")
    fun getIllnesses(): Call<allIllness>
}