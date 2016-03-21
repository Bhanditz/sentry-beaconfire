package com.thierry.beaconfire.module.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thierry.beaconfire.R
import com.thierry.beaconfire.common.BaseFragment
import com.thierry.beaconfire.component.BaseListFragment
import com.thierry.beaconfire.module.project.ProjectListViewModel
import com.thierry.beaconfire.module.project.SettingsListViewModel

/**
 * Created by Thierry on 16/3/9.
 */
class SettingsFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_settings, container, false)
        val fragment = BaseListFragment().setItemLayoutId(R.layout.listitem_settings).setViewModel(SettingsListViewModel())
        this.replaceFragmentByTag(R.id.fragment_content, fragment, "settings")
        return view
    }

}