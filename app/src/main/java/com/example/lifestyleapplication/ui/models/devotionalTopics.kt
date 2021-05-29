package com.example.lifestyleapplication.ui.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class devotionalTopics(
    @SerializedName("TOPIC")
    @Expose
    var topic: String? = null,
    @SerializedName("ID")
    @Expose
    var id: Int? = null
) {

}