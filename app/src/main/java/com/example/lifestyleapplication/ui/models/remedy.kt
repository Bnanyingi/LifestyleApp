package com.example.lifestyleapplication.ui.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class remedy(
    @SerializedName("ID")
    @Expose
    var id: Int? = null,
    @SerializedName("ILLNESS")
    @Expose
    var illness: String? = null,
    @SerializedName("REMEDY")
    @Expose
    var remedyName: String? = null,
    @SerializedName("DESCRIPTION")
    @Expose
    var description: String? = null
) {

}