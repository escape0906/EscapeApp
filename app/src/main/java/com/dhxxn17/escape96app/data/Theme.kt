package com.dhxxn17.escape96app.data

import android.os.Parcel
import android.os.Parcelable

data class Theme(
    val id: Int = 0,
    val title: String = "",
    val location: String = "",
    val thumbnail: String = "",
    val genre: String = "",
    val store: String = "",
    val playTime: Int = 0,
    val recommendedPeople: String = "",
    val maximumPeople: Int = 0
)