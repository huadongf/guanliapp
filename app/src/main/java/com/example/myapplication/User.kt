package com.example.myapplication

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class User(var id:String,var Name: String, var gender: String,var hometown:String,var grade:Long,var urii: String) :
    Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    var idd: Long = 0

}

