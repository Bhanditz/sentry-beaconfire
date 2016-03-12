package com.thierry.beaconfire.module.dashboard

import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.thierry.beaconfire.BR
import com.thierry.beaconfire.R
import com.thierry.beaconfire.common.BaseFragment
import com.thierry.beaconfire.databinding.FragmentListBinding
import com.thierry.beaconfire.module.common.BaseListViewModel
import com.thierry.beaconfire.module.common.BaseListViewModel.FetchDataResult
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.find

/**
 * Created by Thierry on 16/3/11.
 */

class ListFragment() : BaseFragment() {

    var viewModel: DashboardViewModel? = null
    var listView: ListView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mBinding: FragmentListBinding = DataBindingUtil.inflate<FragmentListBinding>(inflater, R.layout.fragment_list, container, false)
        var type = arguments.getString("type")
        listView = mBinding.root.find(R.id.list_view)
        viewModel = DashboardViewModel(type)
        mBinding.viewModel = viewModel
        this.addKVO()
        this.fetchData()
        return mBinding.root
    }

    fun fetchData() {
        showLoading()
        viewModel!!.fetchRemoteData()
    }

    fun addKVO() {
        Log.d(TAG, viewModel.toString())
        viewModel!!.fetchDataResult.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable, propertyId: Int) {
                this@ListFragment.observeHandler(viewModel!!.fetchDataResult.get())
            }
        })
    }

    fun observeHandler(newValue: FetchDataResult) {
        hideLoading()
        Log.d(TAG, "observeHandler" + newValue.toString())
        if (newValue == FetchDataResult.Success) {
            if (viewModel!!.dataArray.count() > 0) {
                listView!!.adapter = DashboardAdapter(this.activity, viewModel!!)
            } else {
//                toast("Nothing to show here, move along")
            }
        } else if (newValue == FetchDataResult.Failed) {
//            toast("Get data failed")
        }
    }
}