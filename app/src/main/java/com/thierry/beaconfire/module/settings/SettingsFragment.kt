package com.thierry.beaconfire.module.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thierry.beaconfire.R
import com.thierry.beaconfire.common.BaseFragment

/**
 * Created by Thierry on 16/3/9.
 */
class SettingsFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_settings, container, false);
        return view
    }

}