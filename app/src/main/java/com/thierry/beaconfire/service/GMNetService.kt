package com.thierry.beaconfire.service

import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import android.webkit.CookieManager
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.fuel.core.Manager
import com.thierry.beaconfire.App
import com.thierry.beaconfire.util.Constants
import org.jetbrains.anko.*

/** @brief Http状态枚举 */
enum class HttpStatusCode(val code: Int) {
    HttpStatusCodeSuccess(200),
    HttpStatusCodeError(500),
    HttpStatusCodeUnauthorized(403)
};

/** @brief Http方法枚举 */
enum class HttpMethod {
    HttpMethodGet,
    HttpMethodPost,
    HttpMethodPut,
    HttpMethodDelete,
};

/**
 * Created by Thierry on 16/2/25.
 */
class GMNetService private constructor() {

    val localBroadcastManager = LocalBroadcastManager.getInstance(App.instance);
    val cookieManager: CookieManager = CookieManager.getInstance()
    val TAG = GMNetService::class.java.canonicalName
    var apiHost: String = Constants.Host

    private object Holder {
        val INSTANCE = GMNetService()
    }

    companion object {
        val instance: GMNetService by lazy { Holder.INSTANCE }
    }

    fun doRequest(remoteUrl: String, method: HttpMethod, params: List<Pair<String, Any?>>?, success: (Response) -> Unit, failed: (String) -> Unit) {
        Log.d(TAG, remoteUrl)
        async() {
            Manager.instance.baseHeaders = mapOf("Cookie" to cookieManager.getCookie(apiHost))
            when (method) {
                HttpMethod.HttpMethodGet ->
                    remoteUrl.httpGet(params).responseJson { request, response, result ->
                        uiThread() {
                            handleResponse(response, success, failed)
                        }
                    }
                HttpMethod.HttpMethodPost ->
                    remoteUrl.httpPost(params).responseJson { request, response, result ->
                        uiThread() {
                            handleResponse(response, success, failed)
                        }
                    }
                HttpMethod.HttpMethodDelete ->
                    remoteUrl.httpDelete(params).responseJson { request, response, result ->
                        uiThread() {
                            handleResponse(response, success, failed)
                        }
                    }
                HttpMethod.HttpMethodPut ->
                    remoteUrl.httpPut(params).responseJson { request, response, result ->
                        uiThread() {
                            handleResponse(response, success, failed)
                        }
                    }
            }

        }
    }

    fun handleResponse(response: Response, success: (Response) -> Unit, failed: (String) -> Unit) {
        Log.d(TAG, "handleResponse")
        val statusCode = response.httpStatusCode
        if (statusCode == HttpStatusCode.HttpStatusCodeSuccess.code) {
            success(response)
        } else if (statusCode == HttpStatusCode.HttpStatusCodeUnauthorized.code) {
            Log.d(TAG, "HttpStatusCode.HttpStatusCodeUnauthorized")
            this.sendLoginExpired()
        } else {
            failed("Request Data Error")
        }
    }

    fun sendLoginExpired() {
        val intent: Intent = Intent();
        intent.action = Constants.Broadcast.LoginExpired
        localBroadcastManager.sendBroadcast(intent)
    }

}