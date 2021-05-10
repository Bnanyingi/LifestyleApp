package com.example.lifestyleapplication.data.remote.quote

import com.example.lifestyleapplication.data.remote.quote.models.Quote
import retrofit2.Response
import retrofit2.http.GET

interface QuotesApiService {

    @GET("today")
    suspend fun getQuote(): Response<Quote>

}