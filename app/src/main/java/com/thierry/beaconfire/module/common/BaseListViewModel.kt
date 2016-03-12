package com.thierry.beaconfire.module.common

import android.databinding.BaseObservable
import android.databinding.ObservableField
import android.util.Log
import com.google.gson.Gson
import com.thierry.beaconfire.service.GMNetService
import com.thierry.beaconfire.service.HttpMethod

/**
 * Created by Thierry on 16/3/11.
 */

abstract class BaseListViewModel : BaseObservable() {

    val TAG = this.javaClass.canonicalName
    var remoteUrl = ""
    val gson = Gson()
    var message = ""
    var cursor = ""
    var params: List<Pair<String, Any?>>? = null
    final var fetchDataResult: ObservableField<FetchDataResult> = ObservableField()

    open var dataArray: List<Any> = mutableListOf()

    fun fetchRemoteData() {
        this.buildRemoteUrl()
        this.buildParams()
        GMNetService.instance.doRequest(remoteUrl, HttpMethod.HttpMethodGet, params, { response ->
            Log.d(TAG, "remoteUrl" + remoteUrl)
            if (remoteUrl == "") {
                fetchDataResult.set(FetchDataResult.Failed)
            }

            try {
                val jsonString: String = String(response.data)
                buildData(jsonString)
                fetchDataResult.set(FetchDataResult.Success)
            } catch (e: Exception) {
                fetchDataResult.set(FetchDataResult.Failed)
                message = "Fetch Data Error"
            }
        }, { error ->
            Log.d(TAG, "error" + error)
            fetchDataResult.set(FetchDataResult.Failed)
            message = error
        })
    }

    abstract fun buildData(dataString: String)

    abstract fun buildRemoteUrl()

    abstract fun buildParams()

    enum class FetchDataResult() {
        Normal,
        Success,
        Failed
    }
}