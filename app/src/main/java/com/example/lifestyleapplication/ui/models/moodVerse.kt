package com.example.lifestyleapplication.ui.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class moodVerse(
    @SerializedName("ID")
    @Expose
    var id: Int? = null,
    @SerializedName("NAME")
    @Expose
    var name: String? = null,
    @SerializedName("VERSE")
    @Expose
    var verse: String? = null,
    @SerializedName("CHAPTER")
    @Expose
    var chapter: String? = null
    ) {
}