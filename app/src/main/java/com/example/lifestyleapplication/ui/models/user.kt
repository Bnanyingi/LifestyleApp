package com.example.lifestyleapplication.ui.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class user(
    @SerializedName("ID")
    @Expose
    var id: Int? = null,
    @SerializedName("USERNAME")
    @Expose
    var username: String? = null,
    @SerializedName("EMAIL")
    @Expose
    var email: String? = null,
    @SerializedName("GENDER")
    @Expose
    var gender: String? = null,
    @SerializedName("AGE")
    @Expose
    var age: String? = null,
    @SerializedName("CITY")
    @Expose
    var city: String? = null,
    @SerializedName("STATE")
    @Expose
    var state: String? = null,
    @SerializedName("COUNTRY")
    @Expose
    var country: String? = null,
    @SerializedName("DIET")
    @Expose
    var diet: String? = null,
    @SerializedName("HEALTHCONDITION")
    @Expose
    var healthcondition: String? = null,
    @SerializedName("DISABILITY")
    @Expose
    var disability: String? = null,
    @SerializedName("EATTIMES")
    @Expose
    var timesyoueat: String? = null,
    @SerializedName("MEAL")
    @Expose
    var meal: String? = null
) {
}