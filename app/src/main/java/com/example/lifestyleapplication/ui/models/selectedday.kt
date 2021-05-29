package com.example.lifestyleapplication.ui.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class selectedday(
    @SerializedName("ID")
    @Expose
    var id: Int? = null,
    @SerializedName("DAY")
    @Expose
    var day: String? = null,
    @SerializedName("MEAL")
    @Expose
    var meal: String? = null,
    @SerializedName("MEALIMAGEURL")
    @Expose
    var mealimage: String? = null
) {

}