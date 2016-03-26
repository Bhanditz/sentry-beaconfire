package com.thierry.beaconfire.module.project

import android.os.Bundle
import com.thierry.beaconfire.R
import com.thierry.beaconfire.common.BaseActivity
import com.thierry.beaconfire.component.ListFragment

/**
 * Created by Thierry on 16/3/16.
 */
class EventListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        val project_slug = intent.getStringExtra("project_slug")
        val project_name = intent.getStringExtra("project_name")
        this.actionBar.title = project_name
        val fragment = ListFragment().setItemLayoutId(R.layout.listitem_event).setViewModel(EventListViewModel(project_slug))
        this.replaceFragmentByTag(R.id.fragment_content, fragment, "event_list")
    }

}