package com.example.eventdemo.data.source.network

import com.example.eventdemo.data.model.EventDemoResponseModel
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("home?")
    fun getMatchesList(@Query("norm") norm: String?,
                       @Query("filterBy") filterBy: String?,
                       @Query("city") city: String?): Single<retrofit2.Response<ResponseBody>>
}