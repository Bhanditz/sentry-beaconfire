package com.thierry.beaconfire.module.dashboard

import android.os.Bundle
import android.support.v4.app.FragmentTabHost
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thierry.beaconfire.common.BaseFragment
import com.thierry.beaconfire.R
import com.thierry.beaconfire.component.BaseListFragment
import org.jetbrains.anko.find

/**
 * Created by Thierry on 16/3/9.
 */
class DashboardFragment : BaseFragment() {

    var mTabHost: FragmentTabHost? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_dashboard, container, false);
        mTabHost = view?.find<FragmentTabHost>(R.id.tab_host)
        this.setUpTabs(mTabHost!!)
        return view
    }

    fun setUpTabs(mTabHost: FragmentTabHost) {
        mTabHost.setup(this.activity, childFragmentManager, R.id.tab_content);
        this.addTab("Assigned")
        this.addTab("New")
    }

    fun addTab(type: String) {
        var bundle = Bundle()
        bundle.putSerializable("ViewModel", DashboardViewModel(type))
        bundle.putInt("ItemLayoutId", R.layout.listitem_dashboard)
        mTabHost?.addTab(mTabHost?.newTabSpec(type)?.setIndicator(type),
                BaseListFragment::class.java, bundle)
    }
}