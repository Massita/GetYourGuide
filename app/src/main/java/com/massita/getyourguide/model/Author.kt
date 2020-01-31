package com.massita.getyourguide.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Author(
    val fullName: String?,
    val country: String?,
    val photo: String?
) : Parcelable