package com.example.lifestyleapplication.ui.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AllSelectedDays(
    @SerializedName("status")
    @Expose
    var status: String? = null,
    @SerializedName("message")
    @Expose
    var message: String? = null,
    @SerializedName("data")
    @Expose
    var data: ArrayList<selectedday> = ArrayList()
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        TODO("data")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(status)
        parcel.writeString(message)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AllSelectedDays> {
        override fun createFromParcel(parcel: Parcel): AllSelectedDays {
            return AllSelectedDays(parcel)
        }

        override fun newArray(size: Int): Array<AllSelectedDays?> {
            return arrayOfNulls(size)
        }
    }
}