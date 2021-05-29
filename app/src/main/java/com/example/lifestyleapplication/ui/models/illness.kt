package com.example.lifestyleapplication.ui.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class illness(
    @SerializedName("ID")
    @Expose
    var id: Int? = null,
    @SerializedName("ILLNESS")
    @Expose
    var name: String? = null
) {
}