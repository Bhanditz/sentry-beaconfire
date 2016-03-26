package com.thierry.beaconfire.module.project

import android.view.View
import android.widget.AdapterView
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.thierry.beaconfire.R
import com.thierry.beaconfire.model.IssueBean
import com.thierry.beaconfire.module.common.BaseListViewModel
import com.thierry.beaconfire.util.Constants
import org.jetbrains.anko.startActivity

/**
 * Created by Thierry on 16/3/11.
 */
open class EventListViewModel(val project_slug: String) : BaseListViewModel() {

    val statsPeriod = "24h"
    val query = "is:unresolved"
    val limit = 25

    override fun buildData(dataString: String) {
        dataArray = Gson().fromJson<List<IssueBean>>(dataString)
    }

    override fun buildRemoteUrl() {
        remoteUrl = Constants.API.Events.format(project_slug)
    }

    override fun buildParams() {
        params = listOf(Pair("statsPeriod", statsPeriod), Pair("query", query), Pair("limit", limit), Pair("cursor", cursor))
    }

    fun getTitle(position: Int): String {
        val issue: IssueBean = dataArray[position] as IssueBean
        return issue.culprit
    }

    fun getDetail(position: Int): String {
        val issue: IssueBean = dataArray[position] as IssueBean
        return issue.title
    }

    fun getFlag(position: Int): String {
        val issue: IssueBean = dataArray[position] as IssueBean
        return "Events:${issue.count}  Users:${(issue.userCount)}"
    }

    fun getLevelColor(position: Int): Int {
        val issue: IssueBean = dataArray[position] as IssueBean
        if (issue.level == "error") {
            return R.color.LevelError
        } else if (issue.level == "info") {
            return R.color.LevelInfo
        } else if (issue.level == "warning") {
            return R.color.LevelWarning
        } else {
            return R.color.LevelDefault
        }
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, item: Long) {
        val issue: IssueBean = dataArray[position] as IssueBean
        view?.context?.startActivity<EventDetailActivity>("url" to issue.permalink)
    }

}