package com.example.lifestyleapplication

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Quote (
    @SerializedName("id") @Expose
    var id: Int? = null,
    @SerializedName("dialogue")
    @Expose
    var dialogue: Boolean? = null,

    @SerializedName("private")
    @Expose
    var _private: Boolean? = null,

    @SerializedName("tags")
    @Expose
    private val tags: List<String>? = null,

    @SerializedName("url")
    @Expose
    private val url: String? = null,

    @SerializedName("favorites_count")
    @Expose
    private val favoritesCount: Int? = null,

    @SerializedName("upvotes_count")
    @Expose
    private val upvotesCount: Int? = null,

    @SerializedName("downvotes_count")
    @Expose
    private val downvotesCount: Int? = null,

    @SerializedName("author")
    @Expose
    var author: String? = null,

    @SerializedName("author_permalink")
    @Expose
    private val authorPermalink: String? = null,

    @SerializedName("body")
    @Expose
    var body: String? = null
)