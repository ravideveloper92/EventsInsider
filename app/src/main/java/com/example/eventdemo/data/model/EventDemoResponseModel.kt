package com.example.eventdemo.data.model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class EventDemoResponseModel(
    @SerializedName("banners")
    val banners: ArrayList<Banner>,
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("digital_event_groups")
    val digitalEventGroups: ArrayList<DigitalEventGroup>,
    @SerializedName("digital_event_groups_description")
    val digitalEventGroupsDescription: String,
    @SerializedName("featured")
    val featured: ArrayList<Featured>,
    @SerializedName("groups")
    val groups: ArrayList<String>,
    var lists: List= List(),
    var pick: Picks= Picks(),
    @SerializedName("popular")
    val popular: ArrayList<Featured>,
    @SerializedName("tags")
    val tags: ArrayList<String>

) : Parcelable