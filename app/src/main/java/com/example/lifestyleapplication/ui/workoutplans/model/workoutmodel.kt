package com.example.lifestyleapplication.ui.workoutplans.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class workoutmodel(
    @SerializedName("ID")
    @Expose
    var id: Int? = null,
    @SerializedName("DAY")
    @Expose
    var day: String? = null,
    @SerializedName("WORKOUTNUMBER")
    @Expose
    var workoutnumber: String? = null,
    @SerializedName("EXERCISE")
    @Expose
    var exercise: String? = null,
    @SerializedName("EXERCISE_IMAGE")
    @Expose
    var exerciseimg: String? = null,
    @SerializedName("EXERCISE_DESCRIPTION")
    @Expose
    var exercisedescription: String? = null,
    @SerializedName("SECTION")
    @Expose
    var section: String? = null
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(day)
        parcel.writeString(workoutnumber)
        parcel.writeString(exercise)
        parcel.writeString(exerciseimg)
        parcel.writeString(exercisedescription)
        parcel.writeString(section)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<workoutmodel> {
        override fun createFromParcel(parcel: Parcel): workoutmodel {
            return workoutmodel(parcel)
        }

        override fun newArray(size: Int): Array<workoutmodel?> {
            return arrayOfNulls(size)
        }
    }
}