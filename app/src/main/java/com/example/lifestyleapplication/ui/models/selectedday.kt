package com.example.lifestyleapplication.ui.models

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class selectedday(
    @SerializedName("ID")
    @Expose
    var id: Int? = null,
    @SerializedName("DAY")
    @Expose
    var day: String? = null,
    @SerializedName("MEAL")
    @Expose
    var meal: String? = null,
    @SerializedName("MEALIMAGEURL")
    @Expose
    var mealimage: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(day)
        parcel.writeString(meal)
        parcel.writeString(mealimage)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<selectedday> {
        override fun createFromParcel(parcel: Parcel): selectedday {
            return selectedday(parcel)
        }

        override fun newArray(size: Int): Array<selectedday?> {
            return arrayOfNulls(size)
        }
    }

}