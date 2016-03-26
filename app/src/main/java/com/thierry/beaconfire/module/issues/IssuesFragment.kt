package com.thierry.beaconfire.module.issues

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thierry.beaconfire.R
import com.thierry.beaconfire.common.BaseFragment
import com.thierry.beaconfire.component.ListFragment

/**
 * Created by Thierry on 16/3/9.
 */
class IssuesFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_container, container, false)
        val fragment = ListFragment().setItemLayoutId(R.layout.listitem_issues).setViewModel(IssuesViewModel())
        this.replaceFragmentByTag(R.id.fragment_content, fragment, "settings")
        return view
    }

}