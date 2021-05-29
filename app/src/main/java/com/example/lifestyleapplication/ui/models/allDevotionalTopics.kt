package com.example.lifestyleapplication.ui.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class allDevotionalTopics(
    @SerializedName("data")
    @Expose
    var data: ArrayList<devotionalTopics> = ArrayList()
) {

}