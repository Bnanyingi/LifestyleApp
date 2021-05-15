package com.example.lifestyleapplication.ui.models

import android.os.Parcel
import android.os.Parcelable
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
)  : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(author)
        parcel.writeStringList(lines)
        parcel.writeString(lineCount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<poem> {
        override fun createFromParcel(parcel: Parcel): poem {
            return poem(parcel)
        }

        override fun newArray(size: Int): Array<poem?> {
            return arrayOfNulls(size)
        }
    }
}