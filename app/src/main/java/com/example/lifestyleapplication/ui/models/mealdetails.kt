package com.example.lifestyleapplication.ui.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class mealdetails(
    @SerializedName("ID")
    @Expose
    var status: String? = null,
    @SerializedName("MEALPLAN")
    @Expose
    var message: String? = null,
    @SerializedName("MEALDURATION")
    @Expose
    var mealDuration: String? = null,
    @SerializedName("NAME")
    @Expose
    var name: String? = null,
    @SerializedName("AGECATEGORY")
    @Expose
    var ageCategory: String? = null,
    @SerializedName("HEALTHCONDITION")
    @Expose
    var healthCondition: String? = null,
    @SerializedName("BODYGOALS")
    @Expose
    var bodyGoals: String? = null,
    @SerializedName("BODYTYPE")
    @Expose
    var bodyType: String? = null,
    @SerializedName("WEIGHT")
    @Expose
    var weight: String? = null,
    @SerializedName("PERIOD")
    @Expose
    var period: String? = null,
    @SerializedName("IMAGEURL")
    @Expose
    var image: String? = null,
    @SerializedName("DAY")
    @Expose
    var day: String? = null

) {
}