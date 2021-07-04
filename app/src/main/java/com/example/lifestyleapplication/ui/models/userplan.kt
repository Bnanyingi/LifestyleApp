package com.example.lifestyleapplication.ui.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class userplan(
    @SerializedName("EMAIL")
    @Expose
    var email: String? = null,
    @SerializedName("ID")
    @Expose
    var id: String? = null,
    @SerializedName("DAY")
    @Expose
    var day: String? = null,
    @SerializedName("DAYS")
    @Expose
    var days: String? = null,
    @SerializedName("BREAKFAST")
    @Expose
    var breakfast: String? = null,
    @SerializedName("LUNCH")
    @Expose
    var lunch: String? = null,
    @SerializedName("DINNER")
    @Expose
    var dinner: String? = null,
    @SerializedName("DESSERT")
    @Expose
    var dessert: String? = null

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(email)
        parcel.writeString(id)
        parcel.writeString(day)
        parcel.writeString(days)
        parcel.writeString(breakfast)
        parcel.writeString(lunch)
        parcel.writeString(dinner)
        parcel.writeString(dessert)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<userplan> {
        override fun createFromParcel(parcel: Parcel): userplan {
            return userplan(parcel)
        }

        override fun newArray(size: Int): Array<userplan?> {
            return arrayOfNulls(size)
        }
    }
}