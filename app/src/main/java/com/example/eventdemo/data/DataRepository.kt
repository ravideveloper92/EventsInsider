package com.example.eventdemo.data

import android.util.Log
import com.example.eventdemo.data.model.EventDemoResponseModel
import com.example.eventdemo.data.model.Featured
import com.example.eventdemo.data.source.network.ApiService
import com.example.eventdemo.util.StateLiveData
import com.example.eventdemo.util.Utility
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import java.lang.reflect.Type
import javax.inject.Inject


class DataRepository
@Inject constructor( private val apiService: ApiService) {
    val TAG=DataRepository::class.java.simpleName
    var mStateLiveData: StateLiveData<List<EventDemoResponseModel>> = StateLiveData()
    private val disposables = CompositeDisposable()


    fun onClear() {

    }

    fun getShadiMatchesListFromNetworkOrDb(): StateLiveData<List<EventDemoResponseModel>>? {
        Utility.printLog(TAG,"getShadiMatchesListFromNetworkOrDb")
        disposables.add( apiService.getMatchesList("1","go-out","mumbai")
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(object : DisposableSingleObserver<retrofit2.Response<ResponseBody>>() {
                override fun onError(e: Throwable) {
                    //Handle error
                    Utility.printLog(TAG,"onError "+ e.toString())
                }
                override fun onSuccess(response: Response<ResponseBody>) {
                    val result = JSONObject(response.body()?.string())
                    var eventDemoResponseModel = Gson().fromJson(result.toString(), EventDemoResponseModel::class.java)
                    val list_masterList_json = result.getJSONObject("list").getJSONObject("masterList").toString()
                    val picks_masterList_json = result.getJSONObject("picks").getJSONObject("masterList").toString()
                    val type: Type = object : TypeToken<Map<String?, Featured>?>() {}.type
                    val list_masterList_myMap: Map<String, Featured> = Gson().fromJson(list_masterList_json, type)
                    val picks_masterList_myMap: Map<String, Featured> = Gson().fromJson(picks_masterList_json, type)
                    val list_masterList:ArrayList<Featured> = ArrayList(list_masterList_myMap.values)
                    val picks_masterList:ArrayList<Featured> = ArrayList(picks_masterList_myMap.values)
                    eventDemoResponseModel.lists=com.example.eventdemo.data.model.List()
                    eventDemoResponseModel.pick=com.example.eventdemo.data.model.Picks()
                    eventDemoResponseModel.lists.master_jobject=list_masterList
                    eventDemoResponseModel.pick.master_jobject=picks_masterList
                    mStateLiveData.postSuccess(eventDemoResponseModel)
                }
            }))
        return mStateLiveData
    }
}