package com.example.lifestyleapplication.ui.retrofit

import com.example.lifestyleapplication.ui.constants.constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SelectedTitleRetrofit {
    companion object{
        fun getRetrofit(): Retrofit{
            return Retrofit.Builder()
                .baseUrl(constants.AUTHORS_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}