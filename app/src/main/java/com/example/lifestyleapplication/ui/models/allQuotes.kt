package com.example.lifestyleapplication.ui.models

import com.example.lifestyleapplication.Quote
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class allQuotes(
    @SerializedName("qotd_date") @Expose
    var qotdDate: String? = null,
    @SerializedName("quote")
    @Expose
    var quote: Quote? = null
) {
}