package com.example.lifestyleapplication.ui.models

import android.os.Parcel
import android.os.Parcelable

class verse(
    var chapter: String? = null,
    var verse: String? = null
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(chapter)
        parcel.writeString(verse)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<verse> {
        override fun createFromParcel(parcel: Parcel): verse {
            return verse(parcel)
        }

        override fun newArray(size: Int): Array<verse?> {
            return arrayOfNulls(size)
        }
    }
}