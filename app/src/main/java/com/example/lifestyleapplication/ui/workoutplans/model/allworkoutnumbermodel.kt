package com.example.lifestyleapplication.ui.workoutplans.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class allworkoutnumbermodel(
    @SerializedName("message")
    @Expose
    var message: String? = null,
    @SerializedName("data")
    @Expose
    var data: ArrayList<workoutnumbermodel> = ArrayList()
) {
}