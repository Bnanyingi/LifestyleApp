package com.example.lifestyleapplication.ui.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class author(
    @SerializedName("authors")
    @Expose
    var author: ArrayList<String> = ArrayList()
) {
}