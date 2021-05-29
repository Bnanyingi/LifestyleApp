package com.example.lifestyleapplication.ui.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class allDevotions(
    @SerializedName("data")
    @Expose
    var data: ArrayList<devotions> = ArrayList()
) {
}