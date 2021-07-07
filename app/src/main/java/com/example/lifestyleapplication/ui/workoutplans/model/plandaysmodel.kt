package com.example.lifestyleapplication.ui.workoutplans.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class plandaysmodel(
    @SerializedName("ID")
    @Expose
    var id: Int? = null,
    @SerializedName("DAY")
    @Expose
    var day: String? = null,
    @SerializedName("IMAGE")
    @Expose
    var image: String? = null
) {

}