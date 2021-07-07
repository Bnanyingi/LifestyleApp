package com.example.lifestyleapplication.ui.workoutplans.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class workoutnumbermodel(
    @SerializedName("ID")
    @Expose
    var id: Int? = null,
    @SerializedName("DAY")
    @Expose
    var day: String? = null,
    @SerializedName("WORKOUTNUMBER")
    @Expose
    var workoutnumber: String? = null,
    @SerializedName("WORKOUTBODYPART")
    @Expose
    var workoutbodypart: String? = null,
    @SerializedName("SECTION")
    @Expose
    var section: String? = null,
    @SerializedName("IMAGE")
    @Expose
    var img: String? = null
) {
}