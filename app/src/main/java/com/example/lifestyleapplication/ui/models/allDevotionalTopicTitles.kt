package com.example.lifestyleapplication.ui.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class allDevotionalTopicTitles(
    @SerializedName("data")
    @Expose
    var data: ArrayList<devotionalTopicTitles> = ArrayList()
) {
}