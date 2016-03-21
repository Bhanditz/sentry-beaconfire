package com.thierry.beaconfire.module.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thierry.beaconfire.R
import com.thierry.beaconfire.common.BaseFragment
import com.thierry.beaconfire.component.BaseWebViewFragment
import com.thierry.beaconfire.util.Constants

/**
 * Created by Thierry on 16/3/9.
 */
class StatsFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_stats, container, false);
        val fragment = BaseWebViewFragment(Constants.Web.Stats, webViewBlock = { url -> true })
        this.replaceFragmentByTag(R.id.fragment_content, fragment, "stats")
        return view
    }

}