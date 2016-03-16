package com.thierry.beaconfire.module.common

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.app.FragmentTabHost
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

open class BaseWebViewFragment(val url: String, val webViewBlock: (url: String) -> Boolean?) : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_webview, container, false);
        val webView: WebView = view!!.find<WebView>(R.id.web_view)
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webView.setWebViewClient(WebViewClientBase(this))
        webView.loadUrl(url);
        return view
    }

    fun shouldOverrideUrlLoading(url: String): Boolean {
        if (webViewBlock != null) {
            return webViewBlock(url)!!
        } else {
            return true
        }
    }

    private class WebViewClientBase(var context: BaseWebViewFragment) : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            return context.shouldOverrideUrlLoading(url)
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
            context.toast(description)
        }


        override fun doUpdateVisitedHistory(view: WebView, url: String, isReload: Boolean) {
            super.doUpdateVisitedHistory(view, url, isReload);
        }
    }
}