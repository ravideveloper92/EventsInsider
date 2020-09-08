package com.example.eventdemo.ui

import androidx.lifecycle.ViewModel
import com.example.eventdemo.data.DataRepository
import com.example.eventdemo.data.model.EventDemoResponseModel
import com.example.eventdemo.util.StateLiveData
import com.example.eventdemo.util.Utility
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: DataRepository)
    : ViewModel() {
    var EventDemoListLiveData: StateLiveData<List<EventDemoResponseModel>>? = null
    val TAG=MainViewModel::class.java.simpleName
    init {
        Utility.printLog(TAG,"init")
        loadData()
    }

    public fun loadData() {
        EventDemoListLiveData = repository.getShadiMatchesListFromNetworkOrDb()
    }

    override fun onCleared() {
        super.onCleared()
        repository.onClear()
    }
}