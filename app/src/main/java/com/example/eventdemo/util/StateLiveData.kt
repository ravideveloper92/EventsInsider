package com.example.eventdemo.util

import androidx.lifecycle.MutableLiveData
import com.example.eventdemo.data.model.EventDemoResponseModel
import com.example.eventdemo.data.model.Featured

class StateLiveData<T> : MutableLiveData<StateLiveData<T>>() {
    enum class DataStatus {
        SUCCESS, ERROR, LOADING
    }
    var data: EventDemoResponseModel? =null
    var error:Throwable?=null
    var status:DataStatus?=null

    fun loading(): StateLiveData<T> {
        status = DataStatus.LOADING
        data = null
        error = null
        return this
    }

    fun success(data: EventDemoResponseModel): StateLiveData<T> {
        status = DataStatus.SUCCESS
        this.data = data
        error = null
        return this
    }

    fun error(error: Throwable): StateLiveData<T> {
        status = DataStatus.ERROR
        data = null
        this.error = error
        return this
    }

    fun setLoading() {
        value = StateLiveData<T>().loading()
    }

    fun postSuccess(data: EventDemoResponseModel) {
        postValue(StateLiveData<T>().success(data))
    }

    fun postError(throwable: Throwable) {
        postValue(StateLiveData<T>().error(throwable))
    }

}