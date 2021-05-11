package com.example.lifestyleapplication.ui.retrofit

import com.example.lifestyleapplication.ui.constants.constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class quotesRetrofit {
    companion object{
        fun getQuote(): Retrofit{
            return Retrofit.Builder()
                .baseUrl(constants.QUOTE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}