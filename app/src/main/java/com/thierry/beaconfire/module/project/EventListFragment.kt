package com.thierry.beaconfire.module.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thierry.beaconfire.R
import com.thierry.beaconfire.common.BaseFragment
import com.thierry.beaconfire.module.common.BaseListFragment

/**
 * Created by Thierry on 16/3/16.
 */
class EventListFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_projects, container, false);
        val fragment = BaseListFragment().setItemLayoutId(R.layout.listitem_project).setViewModel(ProjectListViewModel())
        this.replaceFragmentByTag(R.id.fragment_content, fragment, "projects")
        return view
    }

}