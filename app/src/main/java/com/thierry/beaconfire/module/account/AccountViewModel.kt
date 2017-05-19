package com.thierry.beaconfire.module.account

import android.content.Intent
import android.support.v4.content.LocalBroadcastManager
import android.view.View
import android.widget.AdapterView
import com.thierry.beaconfire.App
import com.thierry.beaconfire.common.BaseActivity
import com.thierry.beaconfire.model.IssueBean
import com.thierry.beaconfire.model.SimpleBean
import com.thierry.beaconfire.module.common.BaseListViewModel
import com.thierry.beaconfire.module.issues.AssignedToMeActivity
import com.thierry.beaconfire.module.issues.BookmarksActivity
import com.thierry.beaconfire.util.Constants
import org.jetbrains.anko.startActivity

/**
 * Created by Thierry on 16/3/11.
 */
class AccountViewModel : BaseListViewModel() {

    val localBroadcastManager = LocalBroadcastManager.getInstance(App.instance)

    override fun fetchRemoteData() {
        dataArray = listOf(SimpleBean("Sign Out", ""))
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
        val settings: SimpleBean = dataArray[position] as SimpleBean
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