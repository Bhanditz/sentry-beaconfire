package com.thierry.beaconfire.module.common

import android.databinding.DataBindingUtil
import android.databinding.Observable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import com.thierry.beaconfire.R
import com.thierry.beaconfire.common.BaseFragment
import com.thierry.beaconfire.databinding.FragmentListBinding
import com.thierry.beaconfire.module.common.BaseListViewModel.FetchDataResult
import org.jetbrains.anko.find

/**
 * Created by Thierry on 16/3/11.
 */

class BaseListFragment() : BaseFragment() {

    var listView: ListView? = null
    var viewModel: BaseListViewModel? = null
    var layoutId: Int? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.initParams()
        val view = initBinding(inflater, container)
        this.addKVO()
        this.fetchData()
        return view
    }

    fun initParams() {
        viewModel = this.arguments.getSerializable("ViewModel") as BaseListViewModel
        layoutId = this.arguments.getInt("LayoutId")
    }

    fun initBinding(inflater: LayoutInflater?, container: ViewGroup?): View {
        val mBinding: FragmentListBinding = DataBindingUtil.inflate<FragmentListBinding>(inflater, R.layout.fragment_list, container, false)
        val view = mBinding.root
        listView = view.find(R.id.list_view)
        mBinding.viewModel = viewModel
        return view
    }

    fun fetchData() {
        showLoading()
        viewModel?.fetchRemoteData()
    }

    fun addKVO() {
        viewModel?.fetchDataResult?.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable, propertyId: Int) {
                this@BaseListFragment.observeHandler(viewModel!!.fetchDataResult.get())
            }
        })
    }

    fun observeHandler(newValue: FetchDataResult) {
        Log.d(TAG, "newValue" + newValue)
        if (newValue == FetchDataResult.Success) {
            hideLoading()
            if (viewModel!!.dataArray.count() > 0) {
                listView?.adapter = BaseListAdapter(this, viewModel!!, layoutId!!)
            } else {
                toast("Nothing to show here, move along")
            }
        } else if (newValue == FetchDataResult.Failed) {
            hideLoading()
            toast("Get data failed")
        }
    }
}