package com.example.lifestyleapplication.ui.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class day(
    @SerializedName("ID")
    @Expose
    var id: Int? = null,
    @SerializedName("DAY")
    @Expose
    var day: String? = null,
    @SerializedName("DAYURL")
    @Expose
    var url: String? = null
) {
}