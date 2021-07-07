package com.example.lifestyleapplication.ui.workoutplans.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class allworkoutmodel(
    @SerializedName("message")
    @Expose
    var message: String? = null,
    @SerializedName("data")
    @Expose
    var data: ArrayList<workoutmodel> = ArrayList()
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        TODO("data")
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(message)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<allworkoutmodel> {
        override fun createFromParcel(parcel: Parcel): allworkoutmodel {
            return allworkoutmodel(parcel)
        }

        override fun newArray(size: Int): Array<allworkoutmodel?> {
            return arrayOfNulls(size)
        }
    }
}