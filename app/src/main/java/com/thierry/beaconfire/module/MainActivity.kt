package com.thierry.beaconfire.module

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.widget.ArrayAdapter
import android.app.Fragment
import android.app.FragmentManager
import com.thierry.beaconfire.module.dashboard.DashboardFragment
import android.widget.ListView
import com.thierry.beaconfire.R
import com.thierry.beaconfire.common.BaseFragment
import com.thierry.beaconfire.module.project.ProjectListFragment
import com.thierry.beaconfire.module.settings.SettingsFragment
import com.thierry.beaconfire.module.stats.StatsFragment
import com.thierry.beaconfire.util.bindView
import org.jetbrains.anko.onItemClick

class MainActivity : AppCompatActivity() {

    val mDrawerLayout: DrawerLayout by bindView(R.id.drawer_layout)
    var mPlanetTitles: Array<String>? = null
    val mDrawerList: ListView by bindView(R.id.left_drawer);
    val mFragments: List<BaseFragment> = listOf(DashboardFragment(), ProjectListFragment(), StatsFragment(), SettingsFragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPlanetTitles = this.resources.getStringArray(R.array.tabs)
        mDrawerList.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mPlanetTitles)
        mDrawerList.onItemClick { adapterView, view, position, l ->
            selectItem(position);
        }
    }

    fun selectItem(position: Int) {
        val fragment: Fragment = mFragments[position]
        val fragmentManager: FragmentManager = this.fragmentManager;
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
        mDrawerList.setItemChecked(position, true);
        this.actionBar.title = mPlanetTitles!![position];
        mDrawerLayout.closeDrawer(mDrawerList);
    }

}
