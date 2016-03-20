package com.thierry.beaconfire.module.project

import android.view.View
import android.widget.AdapterView
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.thierry.beaconfire.model.IssueBean
import com.thierry.beaconfire.model.ProjectBean
import com.thierry.beaconfire.module.common.BaseListViewModel
import com.thierry.beaconfire.util.Constants
import org.jetbrains.anko.startActivity
import java.text.FieldPosition

/**
 * Created by Thierry on 16/3/11.
 */
class EventListViewModel : BaseListViewModel() {

    var statsPeriod = "24h"
    var query = "is:unresolved"
    var limit = 25
    var project_slug = ""

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

    fun getPermalink(position: Int): String {
        val issue: IssueBean = dataArray[position] as IssueBean
        return issue.permalink
    }

    //    fun getTime(position: Int): String
    //    {
    //        val issue: IssueBean = dataArray[position] as IssueBean
    //        let firstSeen = timeAgoSince(Helper.stringToDate(issue.firstSeen))
    //        let lastSeen = timeAgoSince(Helper.stringToDate(issue.lastSeen))
    //        return "\(lastSeen) - \(firstSeen)"
    //    }

    //    func getLevelColorAtIndexPath(indexPath: NSIndexPath) -> String
    //    {
    //        let issue: IssueObject = _dataArray[indexPath.row]
    //        if issue.level == "error"{
    //            return Color.LevelError
    //        } else if issue.level == "info"{
    //            return Color.LevelInfo
    //        } else if issue.level == "warning"{
    //            return Color.LevelWarning
    //        } else {
    //            return Color.LevelDefault
    //        }
    //    }


}