package com.thierry.beaconfire.module.project

import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import android.view.View
import android.widget.AdapterView
import com.thierry.beaconfire.App
import com.thierry.beaconfire.model.SettingsBean
import com.thierry.beaconfire.module.common.BaseListViewModel
import com.thierry.beaconfire.util.Constants

/**
 * Created by Thierry on 16/3/11.
 */
class SettingsListViewModel : BaseListViewModel() {

    val localBroadcastManager = LocalBroadcastManager.getInstance(App.instance);

    override fun fetchRemoteData() {
        dataArray = listOf(SettingsBean("Sign Out", ""))
        fetchDataResult.set(FetchDataResult.Success)
    }

    override fun buildData(dataString: String) {
    }

    override fun buildRemoteUrl() {
        remoteUrl = ""
    }

    override fun buildParams() {
        params = listOf()
    }

    fun getTitle(position: Int): String {
        val settings: SettingsBean = dataArray[position] as SettingsBean
        return settings.title
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, item: Long) {
        if (position == 0) {
            val intent: Intent = Intent();
            intent.action = Constants.Broadcast.LoginExpired
            localBroadcastManager.sendBroadcast(intent)
        }
    }
}