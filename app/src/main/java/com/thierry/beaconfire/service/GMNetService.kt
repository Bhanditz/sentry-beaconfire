package com.thierry.beaconfire.service

import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import org.jetbrains.anko.*

/** @brief API状态枚举 */
enum class APIStatusCode(val code: Int) {
    APIStatusCodeSuccess(0),
    APIStatusCodeEmpty(1),
    APIStatusCodeFailed(2)
};

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
class GMNetService {

    val TAG = GMNetService.javaClass.canonicalName

    var apiHost: String = ""
    var urlCommonParameters: String = ""

    private object Holder {
        val INSTANCE = GMNetService()
    }

    companion object {
        val instance: GMNetService by lazy { Holder.INSTANCE }
    }

    fun doRequest(url: String, method: HttpMethod, params: List<Pair<String, Any?>>?, success: (Response) -> Unit, failed: (String) -> Unit) {
        async() {
            val remoteUrl = apiHost + url + urlCommonParameters
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
        val statusCode = response.httpStatusCode
        if (statusCode == HttpStatusCode.HttpStatusCodeSuccess.code) {
            success(response)
        } else if (statusCode == HttpStatusCode.HttpStatusCodeUnauthorized.code) {
            failed("没有权限")
        } else {
            failed("网络错误")
        }
    }

}