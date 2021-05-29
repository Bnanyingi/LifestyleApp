package com.example.lifestyleapplication.ui.retrofit

import com.example.lifestyleapplication.ui.constants.constants
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class devotionalRetrofit {
    companion object{
        fun getRetrofit(): Retrofit{
            return Retrofit.Builder()
                .baseUrl(constants.DEVOTIONALS)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}