package com.thierry.beaconfire.module.project

import android.os.Bundle
import android.webkit.WebView
import com.thierry.beaconfire.module.MainActivity
import com.thierry.beaconfire.module.common.BaseWebViewActivity
import com.thierry.beaconfire.util.Constants

/**
 * Created by Thierry on 16/3/16.
 */
/**
 * Created by Thierry on 16/3/11.
 */
class EventDetailActivity : BaseWebViewActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar.title = "Event Detail"
    }
}