package com.example.lifestyleapplication.ui.retrofit

import com.example.lifestyleapplication.ui.constants.constants
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class poemRetrofit {
    companion object{
        fun getPoems(): Retrofit{
            return Retrofit.Builder()
                .baseUrl(constants.AUTHORS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}