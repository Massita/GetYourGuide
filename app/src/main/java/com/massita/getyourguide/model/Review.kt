package com.massita.getyourguide.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Review(
    val id: Int,
    val author: Author?,
    val title: String?,
    val message: String?,
    val enjoyment: String?,
    val isAnonymous: Boolean,
    val rating: Int,
    val created: Date,
    val language: String?,
    val travelerType: String?
) : Parcelable