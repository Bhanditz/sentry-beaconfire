package com.thierry.beaconfire.module.dashboard

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewParent
import android.widget.AdapterView
import android.widget.ListView
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.thierry.beaconfire.R
import com.thierry.beaconfire.model.IssueBean
import com.thierry.beaconfire.module.common.BaseListAdapter
import com.thierry.beaconfire.module.common.BaseListViewModel
import com.thierry.beaconfire.module.project.EventDetailActivity
import com.thierry.beaconfire.util.Constants
import org.jetbrains.anko.startActivity

/**
 * Created by Thierry on 16/3/11.
 */
class DashboardViewModel(val type: String = "Assigned") : BaseListViewModel() {

    val statsPeriod = "24h"
    val per_page = 5
    val status = "unresolved"


    override fun buildData(dataString: String) {
        dataArray = Gson().fromJson<List<IssueBean>>(dataString)
    }

    override fun buildRemoteUrl() {
        if (type == "Assigned") {
            remoteUrl = Constants.API.Assigned
        } else {
            remoteUrl = Constants.API.New
        }
    }

    override fun buildParams() {
        params = listOf(Pair("cursor", cursor), Pair("statsPeriod", statsPeriod), Pair("per_page", per_page), Pair("status", status))
    }

    fun getName(position: Int): String {
        val issue: IssueBean = dataArray[position] as IssueBean
        return issue.title
    }

    fun getDetail(position: Int): String {
        val issue: IssueBean = dataArray[position] as IssueBean
        return "[${issue.project.name})] ${issue.culprit}"
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, item: Long) {
        val issue: IssueBean = dataArray[position] as IssueBean
        view?.context?.startActivity<EventDetailActivity>("url" to issue.permalink)
    }


}