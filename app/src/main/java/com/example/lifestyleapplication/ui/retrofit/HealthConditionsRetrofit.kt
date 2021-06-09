package com.example.lifestyleapplication.ui.retrofit

import com.example.lifestyleapplication.ui.constants.constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HealthConditionsRetrofit {
    companion object{
        fun getRetrofit(): Retrofit{
            return Retrofit.Builder()
                .baseUrl(constants.DEVOTIONALS)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}