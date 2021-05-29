package com.example.lifestyleapplication.ui.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class devotions(
    @SerializedName("ID")
    @Expose
    var id: Int? = null,
    @SerializedName("TOPIC")
    @Expose
    var topic: String? = null,
    @SerializedName("BIBLEREADING")
    @Expose
    var bibleReading: String? = null,
    @SerializedName("AUTHOR")
    @Expose
    var author: String? = null,
    @SerializedName("VERSEONE")
    @Expose
    var verseOne: String? = null,
    @SerializedName("VERSETWO")
    @Expose
    var verseTwo: String? = null,
    @SerializedName("BODY")
    @Expose
    var body: String? = null,
    @SerializedName("AUDIOURL")
    @Expose
    var audioUrl: String? = null
) {
}