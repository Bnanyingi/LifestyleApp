package com.example.lifestyleapplication.ui.interfaces

import com.example.lifestyleapplication.ui.models.allUser
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginInterface {
    @POST("lifestyleuserauth/login.php")
    fun login(
        @Query("email") email: String,
        @Query("password") password: String
    ): Call<allUser>
}