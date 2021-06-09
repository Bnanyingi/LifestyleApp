package com.example.lifestyleapplication.ui.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HealthConditions(
    @SerializedName("ID")
    @Expose
    var id: Int? = null,
    @SerializedName("CONDITION")
    @Expose
    var condition: String? = null

) {
}