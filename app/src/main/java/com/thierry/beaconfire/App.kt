package com.thierry.beaconfire

import android.app.Application
import android.util.Log
import android.webkit.CookieManager
import android.webkit.ValueCallback
import com.thierry.beaconfire.service.GMNetService
import com.thierry.beaconfire.util.Constants
import kotlin.properties.Delegates

/**
 * Created by Thierry on 16/2/29.
 */
class App : Application() {

    companion object {
        var instance: App by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initNet()
    }


    fun initNet() {
        val service: GMNetService = GMNetService.instance
        service.apiHost = Constants.Host;
    }

    fun cookieExist(): Boolean {
        val cookie = CookieManager.getInstance().getCookie(Constants.Host)
        Log.d("Application", cookie)
        if (cookie != null && cookie.contains("sentrysid") && cookie.contains("sudo")) {
            return true
        } else {
            return false
        }
    }

    fun cleanCookie() {
        CookieManager.getInstance().setCookie(Constants.Host, "")
    }


}
