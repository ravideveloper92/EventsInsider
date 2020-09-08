package com.example.eventdemo.data.model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Tag(
    @SerializedName("carousel_position")
    val carouselPosition: Int,
    @SerializedName("_id")
    val id: String,
    @SerializedName("is_carousel")
    val isCarousel: Boolean,
    @SerializedName("is_featured")
    val isFeatured: Boolean,
    @SerializedName("is_pick")
    val isPick: Boolean,
    @SerializedName("is_primary_interest")
    val isPrimaryInterest: Boolean,
    @SerializedName("priority")
    val priority: Int,
    @SerializedName("tag_id")
    val tagId: String
) : Parcelable