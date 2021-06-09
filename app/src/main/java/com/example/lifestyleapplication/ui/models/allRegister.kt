package com.example.lifestyleapplication.ui.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class allRegister(
    @SerializedName("data")
    @Expose
    var data: ArrayList<register> = ArrayList(),
    @SerializedName("message")
    @Expose
    var message: String? = null
) {
}