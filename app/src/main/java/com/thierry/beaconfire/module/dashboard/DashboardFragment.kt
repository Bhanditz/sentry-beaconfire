package com.thierry.beaconfire.module.dashboard

import android.os.Bundle
import android.support.v4.app.FragmentTabHost
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thierry.beaconfire.common.BaseFragment
import com.thierry.beaconfire.R
import org.jetbrains.anko.find

/**
 * Created by Thierry on 16/3/9.
 */
class DashboardFragment : BaseFragment() {

    var mTabHost: FragmentTabHost? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_dashboard, container, false);
        mTabHost = view?.find<FragmentTabHost>(R.id.tab_host)
        setUpTabs(mTabHost!!)
        return view
    }

    fun setUpTabs(mTabHost: FragmentTabHost) {
        mTabHost.setup(this.activity, fragmentManager, R.id.tab_content);
        val bundle1 = Bundle()
        bundle1.putString("type", "Assigned")
        val bundle2 = Bundle()
        bundle2.putString("type", "New")
        mTabHost.addTab(mTabHost.newTabSpec("Assigned").setIndicator("Assigned"), ListFragment::class.java, bundle1)
        mTabHost.addTab(mTabHost.newTabSpec("New").setIndicator("New"), ListFragment::class.java, bundle2)
    }

}