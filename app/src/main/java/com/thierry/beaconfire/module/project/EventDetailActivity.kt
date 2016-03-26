package com.thierry.beaconfire.module.project

import android.os.Bundle
import com.thierry.beaconfire.R
import com.thierry.beaconfire.common.BaseActivity
import com.thierry.beaconfire.component.WebViewFragment
import com.thierry.beaconfire.util.Constants

/**
 * Created by Thierry on 16/3/16.
 */
/**
 * Created by Thierry on 16/3/11.
 */
class EventDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        actionBar.title = "Event Detail"
        val fragment = WebViewFragment(this.intent.getStringExtra("url"), webViewBlock = { url -> true })
        this.replaceFragmentByTag(R.id.fragment_content, fragment, "event_detail")
    }
}