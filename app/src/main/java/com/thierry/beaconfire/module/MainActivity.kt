package com.thierry.beaconfire.module

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTabHost
import com.thierry.beaconfire.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

//    fun setUpTabs(mTabHost: FragmentTabHost, containerId: Int) {
//        mTabHost.setup(this, supportFragmentManager, containerId);
//        mTabHost.addTab(mTabHost.newTabSpec("Dash").setIndicator("Dash"), DashboardFragment::class.java, null)
//        mTabHost.addTab(mTabHost.newTabSpec("Daily").setIndicator("Daily"), DailyFragment::class.java, null)
//        mTabHost.addTab(mTabHost.newTabSpec("Monthly").setIndicator("Monthly"), MonthlyFragment::class.java, null)
//        mTabHost.addTab(mTabHost.newTabSpec("Proxy").setIndicator("Proxy"), ProxyFragment::class.java, null)
//    }
}
