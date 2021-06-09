package com.example.lifestyleapplication.ui.interfaces

import com.example.lifestyleapplication.ui.models.allUser
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface ProfileInterface {
    @POST("lifestyleuserauth/profile.php")
    fun register(
        @Query("id") id: Int,
        @Query("username") username: String,
        @Query("email") email: String,
        @Query("age") age: String,
        @Query("city") city: String,
        @Query("state") state: String,
        @Query("country") country: String,
        @Query("diet") diet: String,
        @Query("healthcondition") healthcondition: String,
        @Query("disability") disability: String,
        @Query("eattimes") eattimes: String,
        @Query("meal") meal: String,
        @Query("gender") gender: String
    ): Call<allUser>
}