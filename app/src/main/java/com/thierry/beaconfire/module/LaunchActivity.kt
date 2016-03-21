package com.thierry.beaconfire.module

import android.os.Bundle
import android.util.Log
import android.webkit.CookieManager
import com.thierry.beaconfire.App
import com.thierry.beaconfire.R
import com.thierry.beaconfire.common.BaseActivity
import com.thierry.beaconfire.util.Constants
import org.jetbrains.anko.async
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

/**
 * Created by Thierry on 16/3/11.
 */
class LaunchActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        actionBar.hide()
        async() {
            Thread.sleep(2000)
            uiThread {
                checkLoginStatus()
            }
        }
    }

    fun checkLoginStatus() {
        if (App.instance.cookieExist()) {
            this.showMainView()
        } else {
            this.showLoginView()
        }
        finish()
    }

    fun showMainView() {
        startActivity<MainActivity>()
    }

    fun showLoginView() {
        App.instance.cleanCookie()
        startActivity<LoginActivity>()
    }

}