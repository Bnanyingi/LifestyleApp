package com.example.lifestyleapplication.ui.interfaces

import com.example.lifestyleapplication.ui.models.allRegister
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface SignUpInterface {
    @POST("lifestyleuserauth/register.php")
    fun register(
        @Query("username") username: String,
        @Query("email") email: String,
        @Query("password") password: String
    ): Call<allRegister>
}