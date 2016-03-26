package com.thierry.beaconfire.module.issues

import android.view.View
import android.widget.AdapterView
import com.thierry.beaconfire.model.SimpleBean
import com.thierry.beaconfire.module.common.BaseListViewModel
import com.thierry.beaconfire.module.issues.AssignedToMeActivity
import com.thierry.beaconfire.module.issues.BookmarksActivity
import com.thierry.beaconfire.module.issues.HistoryActivity
import org.jetbrains.anko.startActivity

/**
 * Created by Thierry on 16/3/11.
 */
class IssuesViewModel : BaseListViewModel() {

    override fun fetchRemoteData() {
        dataArray = listOf(SimpleBean("Assigned To Me", ""), SimpleBean("Bookmarks", ""), SimpleBean("History", ""))
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
            view?.context?.startActivity<AssignedToMeActivity>()
        } else if (position == 1) {
            view?.context?.startActivity<BookmarksActivity>()
        } else if (position == 2) {
            view?.context?.startActivity<HistoryActivity>()
        }
    }
}