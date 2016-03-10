package com.thierry.beaconfire.module.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import com.thierry.beaconfire.R
import com.thierry.beaconfire.common.BaseFragment
import com.thierry.beaconfire.util.bindView

/**
 * Created by Thierry on 16/3/10.
 */

class BaseWebViewFragment : BaseFragment() {

    val webView: WebView by bindView<WebView>(R.id.web_view)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_webview, container, false);
        return view

    }
}