package com.example.lifestyleapplication.ui.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class allUser(
    @SerializedName("data")
    @Expose
    var data: ArrayList<user> = ArrayList(),
    @SerializedName("message")
    @Expose
    var message: String? = null
) {

}