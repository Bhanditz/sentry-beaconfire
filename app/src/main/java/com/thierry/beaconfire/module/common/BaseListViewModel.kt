package com.thierry.beaconfire.module.common

import android.databinding.BaseObservable
import android.databinding.ObservableField
import android.util.Log
import android.view.View
import android.widget.AdapterView
import com.google.gson.Gson
import com.thierry.beaconfire.model.IssueBean
import com.thierry.beaconfire.module.project.EventDetailActivity
import com.thierry.beaconfire.service.GMNetService
import com.thierry.beaconfire.service.HttpMethod
import org.jetbrains.anko.startActivity
import java.io.Serializable
import java.text.FieldPosition

/**
 * Created by Thierry on 16/3/11.
 */

abstract class BaseListViewModel : BaseObservable(), Serializable, AdapterView.OnItemClickListener {

    val TAG = this.javaClass.canonicalName
    var remoteUrl = ""
    var message = ""
    var cursor = ""
    var params: List<Pair<String, Any?>>? = null
    var fetchDataResult: ObservableField<FetchDataResult> = ObservableField()
    open var dataArray: List<Any> = mutableListOf()

    enum class FetchDataResult {
        Normal,
        Success,
        Failed
    }

    open fun fetchRemoteData() {
        this.buildRemoteUrl()
        this.buildParams()
        fetchDataResult.set(FetchDataResult.Normal)
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
            fetchDataResult.set(FetchDataResult.Failed)
            message = error
        })
    }

    abstract fun buildData(dataString: String)

    abstract fun buildRemoteUrl()

    abstract fun buildParams()

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, item: Long) {
    }
}