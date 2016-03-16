package com.thierry.beaconfire.module

import android.os.Bundle
import android.webkit.WebView
import com.thierry.beaconfire.common.BaseActivity
import com.thierry.beaconfire.module.common.BaseWebViewFragment
import com.thierry.beaconfire.util.Constants
import org.jetbrains.anko.startActivity
import com.thierry.beaconfire.R

/**
 * Created by Thierry on 16/3/11.
 */
class LoginActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val fragment = BaseWebViewFragment(Constants.Web.Login, webViewBlock = { url ->
            this.shouldOverrideUrlLoading(url)
        })
        this.actionBar.title = "Login"
        this.replaceFragmentByTag(R.id.fragment_content, fragment, "login")
    }

    fun shouldOverrideUrlLoading(url: String): Boolean {
        if (url == Constants.Web.Root) {
            startActivity<MainActivity>()
            finish()
            return false
        } else {
            return true
        }
    }
}