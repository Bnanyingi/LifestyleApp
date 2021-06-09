package com.example.lifestyleapplication.ui.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class register(
    @SerializedName("ID")
    @Expose
    var id: Int? = null,
    @SerializedName("USERNAME")
    @Expose
    var username: String? = null,
    @SerializedName("EMAIL")
    @Expose
    var email: String? = null
) {
}