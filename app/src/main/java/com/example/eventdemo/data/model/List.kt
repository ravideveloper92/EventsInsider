package com.example.eventdemo.data.model


import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import com.google.gson.JsonElement
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.List
@SuppressLint("ParcelCreator")
@Parcelize
data class List(
    var master_jobject: ArrayList<Featured> =ArrayList<Featured>()
) :Parcelable