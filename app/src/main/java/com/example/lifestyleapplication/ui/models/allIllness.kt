package com.example.lifestyleapplication.ui.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class allIllness(
    @SerializedName("data")
    @Expose
    var data: ArrayList<illness> = ArrayList()
) {
}