package com.thierry.beaconfire.module.project

import android.view.View
import android.widget.AdapterView
import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.thierry.beaconfire.model.ProjectBean
import com.thierry.beaconfire.module.common.BaseListViewModel
import com.thierry.beaconfire.util.Constants
import org.jetbrains.anko.startActivity

/**
 * Created by Thierry on 16/3/11.
 */
class ProjectListViewModel : BaseListViewModel() {

    override fun buildData(dataString: String) {
        dataArray = Gson().fromJson<List<ProjectBean>>(dataString)
    }

    override fun buildRemoteUrl() {
        remoteUrl = Constants.API.Projects
    }

    override fun buildParams() {
        params = listOf()
    }

    fun getTitle(position: Int): String {
        val project: ProjectBean = dataArray[position] as ProjectBean
        return project.name
    }

    fun getSlug(position: Int): String {
        val project: ProjectBean = dataArray[position] as ProjectBean
        return project.slug
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, item: Long) {
        val project: ProjectBean = dataArray[position] as ProjectBean
        view?.context?.startActivity<EventListActivity>("project_slug" to project.slug, "project_name" to project.name)
    }
}