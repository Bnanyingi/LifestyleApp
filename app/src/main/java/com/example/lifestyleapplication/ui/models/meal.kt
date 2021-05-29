package com.example.lifestyleapplication.ui.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class meal(
    @SerializedName("ID")
    @Expose
    var id: Int? = null,
    @SerializedName("DIET")
    @Expose
    var diet: String? = null
) {
}