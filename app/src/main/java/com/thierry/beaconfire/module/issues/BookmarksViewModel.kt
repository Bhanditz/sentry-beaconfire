package com.thierry.beaconfire.module.issues

import com.thierry.beaconfire.module.project.EventListViewModel
import com.thierry.beaconfire.util.Constants

/**
 * Created by Thierry on 16/3/11.
 */
class BookmarksViewModel() : EventListViewModel("") {

    val per_page = 25

    override fun buildRemoteUrl() {
        remoteUrl = Constants.API.Bookmarks
    }

    override fun buildParams() {
        params = listOf(Pair("statsPeriod", statsPeriod), Pair("per_page", per_page), Pair("cursor", cursor))
    }

}