package com.thierry.beaconfire.module.issues

import android.os.Bundle
import com.thierry.beaconfire.R
import com.thierry.beaconfire.common.BaseActivity
import com.thierry.beaconfire.component.ListFragment
import com.thierry.beaconfire.module.project.EventListViewModel

/**
 * Created by Thierry on 16/3/16.
 */
class AssignedToMeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        this.actionBar.title = "Assigned To Me"
        val fragment = ListFragment().setItemLayoutId(R.layout.listitem_event).setViewModel(AssignedToMeViewModel())
        this.replaceFragmentByTag(R.id.fragment_content, fragment, "assigned_to_me")
    }

}