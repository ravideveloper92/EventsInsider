package com.example.eventdemo.data.model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import kotlin.collections.List

@SuppressLint("ParcelCreator")
@Parcelize
data class Picks(
    var master_jobject: ArrayList<Featured> =ArrayList<Featured>()
) : Parcelable