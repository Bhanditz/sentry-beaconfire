package com.thierry.beaconfire.module.dashboard

import android.util.Log
import android.view.View
import com.thierry.beaconfire.R
import com.thierry.beaconfire.model.IssueBean
import com.thierry.beaconfire.module.project.EventDetailActivity
import org.jetbrains.anko.startActivity

/**
 * Created by Thierry on 16/3/15.
 */
class DashboardHandler(val issue: IssueBean) {

    fun onItemClick(view: View) {
        if (view.id == R.id.issue_title) {
            Log.d("DashboardHandler", issue.permalink)
            view.context.startActivity<EventDetailActivity>("url" to issue.permalink)
        }
    }

}