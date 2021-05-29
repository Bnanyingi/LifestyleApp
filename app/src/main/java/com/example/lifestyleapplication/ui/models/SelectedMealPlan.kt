package com.example.lifestyleapplication.ui.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SelectedMealPlan(
    @SerializedName("ID")
    @Expose
    var id: Int? = null,
    @SerializedName("MEALPLAN")
    @Expose
    var mealplan: String? = null,
    @SerializedName("MEALDURATION")
    @Expose
    var mealduration: String? = null,
    @SerializedName("NAME")
    @Expose
    var name: String? = null,
    @SerializedName("AGECATEGORY")
    @Expose
    var agecategory: String? = null,
    @SerializedName("HEALTHCONDITION")
    @Expose
    var healthcondition: String? = null,
    @SerializedName("BODYGOALS")
    @Expose
    var bodygoals: String? = null,
    @SerializedName("BODYTYPE")
    @Expose
    var bodytype: String? = null,
    @SerializedName("WEIGHT")
    @Expose
    var weight: String? = null,
    @SerializedName("PERIOD")
    @Expose
    var period: String? = null,
    @SerializedName("IMAGEURL")
    @Expose
    var imageurl: String? = null
) {

}