package com.example.lifestyleapplication.data.remote.quote

import com.example.lifestyleapplication.utils.QUOTES_BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QuotesClient {

    companion object {
        fun quotesClient(): QuotesApiService {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(QUOTES_BASE_URL)
                .build()
            return retrofit.create(QuotesApiService::class.java)
        }
    }
}