package com.example.lifestyleapplication.data.remote.quote.models


import com.google.gson.annotations.SerializedName

data class QuoteItem(
    @SerializedName("a")
    val a: String,
    @SerializedName("h")
    val h: String,
    @SerializedName("q")
    val q: String
)