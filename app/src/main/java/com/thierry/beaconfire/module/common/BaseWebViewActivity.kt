package com.thierry.beaconfire.module.common

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.thierry.beaconfire.R
import com.thierry.beaconfire.common.BaseActivity
import com.thierry.beaconfire.common.BaseFragment
import com.thierry.beaconfire.module.MainActivity
import com.thierry.beaconfire.util.Constants
import com.thierry.beaconfire.util.bindView
import org.jetbrains.anko.*

/**
 * Created by Thierry on 16/3/10.
 */

open class BaseWebViewActivity() : BaseActivity() {

    val webView: WebView by bindView<WebView>(R.id.web_view)

    open var url: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webView.setWebViewClient(WebViewClientBase(this))
        webView.loadUrl(Constants.Host + url);
    }

    open fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        return true
    }

    private class WebViewClientBase(var context: BaseWebViewActivity) : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            return context.shouldOverrideUrlLoading(view, url)
        }


        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon);
            context.showLoading()
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url);
            context.hideLoading()
        }

        override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            context.hideLoading()
        }


        override fun doUpdateVisitedHistory(view: WebView, url: String, isReload: Boolean) {
            super.doUpdateVisitedHistory(view, url, isReload);
        }
    }
}