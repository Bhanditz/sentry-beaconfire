package com.thierry.beaconfire.module

import android.os.Bundle
import android.webkit.WebView
import com.thierry.beaconfire.module.common.BaseWebViewActivity
import com.thierry.beaconfire.util.Constants
import org.jetbrains.anko.startActivity

/**
 * Created by Thierry on 16/3/11.
 */
class LoginActivity : BaseWebViewActivity() {

    override var url: String = Constants.Web.Login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        if (url == Constants.Web.Root) {
            startActivity<MainActivity>()
            finish()
            return false
        } else {
            return true
        }
    }
}