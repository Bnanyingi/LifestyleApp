package com.example.lifestyleapplication.ui.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class poem(
    @SerializedName("title")
    @Expose
    var title: String? = null,
    @SerializedName("author")
    @Expose
    var author: String? = null,
    @SerializedName("lines")
    @Expose
    var lines: List<String>? = null,
    @SerializedName("linecount")
    @Expose
    var lineCount: String? = null
) {
}