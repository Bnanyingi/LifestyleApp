package com.example.lifestyleapplication.ui.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class allMealDetails(
    @SerializedName("status")
    @Expose
    var status: String? = null,
    @SerializedName("message")
    @Expose
    var message: String? = null,
    @SerializedName("data")
    @Expose
    var data: ArrayList<mealdetails> = ArrayList()
) {
}