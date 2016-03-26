package com.thierry.beaconfire.module

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import android.graphics.Color;
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.support.v4.widget.DrawerLayout
import com.thierry.beaconfire.module.dashboard.DashboardFragment
import android.widget.ListView
import com.balysv.materialmenu.MaterialMenuDrawable.Stroke
import com.balysv.materialmenu.MaterialMenuIcon
import com.thierry.beaconfire.R
import com.thierry.beaconfire.common.BaseActivity
import com.thierry.beaconfire.common.BaseFragment
import com.thierry.beaconfire.module.project.ProjectListFragment
import com.thierry.beaconfire.module.issues.IssuesFragment
import com.thierry.beaconfire.module.stats.StatsFragment
import com.thierry.beaconfire.util.bindView
import org.jetbrains.anko.onItemClick
import android.support.v4.widget.DrawerLayout.DrawerListener
import android.view.MenuItem
import android.view.View
import android.widget.SimpleAdapter
import com.balysv.materialmenu.MaterialMenuDrawable
import com.thierry.beaconfire.App
import com.thierry.beaconfire.module.account.AccountFragment
import com.thierry.beaconfire.util.Constants
import java.util.*

class MainActivity : BaseActivity(), DrawerListener {

    var materialMenu: MaterialMenuIcon? = null
    var isDrawerOpened: Boolean = false
    val mDrawerLayout: DrawerLayout by bindView(R.id.drawer_layout)
    val mFragmentTitles = arrayOf("DashBoard", "Projects", "Stats", "Issues", "Account")
    val mFragmentIcons = arrayOf(R.drawable.icon_drawer_dashboard, R.drawable.icon_drawer_project, R.drawable.icon_drawer_stats, R.drawable.icon_drawer_issues, R.drawable.icon_drawer_account)
    val mDrawerList: ListView by bindView(R.id.left_drawer);
    val mFragments: List<BaseFragment> = listOf(DashboardFragment(), ProjectListFragment(), StatsFragment(), IssuesFragment(), AccountFragment())
    var localBroadcastManager: LocalBroadcastManager? = null
    var mBroadcastReceiver: AuthBroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionBar.setDisplayHomeAsUpEnabled(true)
        this.drawLeftDrawer()
        this.registerBroadcast()
    }

    fun drawLeftDrawer() {
        materialMenu = MaterialMenuIcon(this, Color.WHITE, Stroke.THIN);
        mDrawerList.adapter = SimpleAdapter(this, getData(), R.layout.listitem_drawer, arrayOf("icon", "title"), intArrayOf(R.id.drawer_icon, R.id.drawer_title));
        mDrawerList.onItemClick { adapterView, view, position, l ->
            this.selectItem(position);
        }
        this.selectItem(0)
        mDrawerLayout.setDrawerListener(this)
    }

    fun getData(): ArrayList<Map<String, Any>> {
        val list = ArrayList<Map<String, Any>>()
        for ((index, title: String) in mFragmentTitles.withIndex()) {
            val map: HashMap<String, Any> = HashMap()
            map.put("title", title);
            map.put("icon", mFragmentIcons[index]);
            list.add(map)
        }
        return list
    }

    fun registerBroadcast() {
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        mBroadcastReceiver = AuthBroadcastReceiver(this)
        val intentFilter: IntentFilter = IntentFilter();
        intentFilter.addAction(Constants.Broadcast.LoginExpired);
        localBroadcastManager?.registerReceiver(mBroadcastReceiver, intentFilter);
    }

    fun selectItem(position: Int) {
        this.replaceFragmentByTag(R.id.content_frame, mFragments[position], "fragment" + position)
        mDrawerList.setItemChecked(position, true);
        actionBar.title = mFragmentTitles[position];
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        materialMenu?.syncState(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        materialMenu?.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
        var value = slideOffset
        if (isDrawerOpened) {
            value = 2 - slideOffset
        }
        materialMenu?.setTransformationOffset(MaterialMenuDrawable.AnimationState.BURGER_ARROW, value)
    }

    override fun onDrawerOpened(drawerView: View) {
        isDrawerOpened = true;
    }

    override fun onDrawerClosed(drawerView: View) {
        isDrawerOpened = false;
    }

    override fun onDrawerStateChanged(newState: Int) {
        if (newState == DrawerLayout.STATE_IDLE) {
            if (isDrawerOpened) {
                materialMenu?.state = MaterialMenuDrawable.IconState.ARROW
            } else {
                materialMenu?.state = MaterialMenuDrawable.IconState.BURGER
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (isDrawerOpened) {
                materialMenu?.state = MaterialMenuDrawable.IconState.ARROW
                mDrawerLayout.closeDrawer(mDrawerList)
            } else {
                materialMenu?.state = MaterialMenuDrawable.IconState.BURGER
                mDrawerLayout.openDrawer(mDrawerList)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy();
        localBroadcastManager?.unregisterReceiver(mBroadcastReceiver);
    }

    class AuthBroadcastReceiver(val context: Context) : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            if (intent?.action == Constants.Broadcast.LoginExpired) {
                App.instance.cleanCookie()
                val mIntent: Intent = Intent(context, LoginActivity::class.java)
                mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                context.startActivity(mIntent);
            }
        }
    }

}
