package com.thierry.beaconfire.module

import android.os.Bundle
import com.thierry.beaconfire.App
import com.thierry.beaconfire.R
import com.thierry.beaconfire.common.BaseActivity
import org.jetbrains.anko.doAsync
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
        doAsync {
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