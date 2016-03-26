package com.thierry.beaconfire.component

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
import com.thierry.beaconfire.module.common.BaseListAdapter
import com.thierry.beaconfire.module.common.BaseListViewModel
import com.thierry.beaconfire.module.common.BaseListViewModel.FetchDataResult
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.toast

/**
 * Created by Thierry on 16/3/11.
 */

class ListFragment() : BaseFragment() {

    var listView: ListView? = null
    var viewModel: BaseListViewModel? = null
    var itemLayoutId: Int? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.initParams()
        val view = initBinding(inflater, container)
        this.addKVO()
        this.fetchData()
        return view
    }

    fun setViewModel(viewModel: BaseListViewModel): ListFragment {
        this.viewModel = viewModel
        return this
    }

    fun setItemLayoutId(itemLayoutId: Int): ListFragment {
        this.itemLayoutId = itemLayoutId
        return this
    }

    fun initParams() {
        if (this.arguments != null) {
            viewModel = this.arguments.getSerializable("ViewModel") as BaseListViewModel
            itemLayoutId = this.arguments.getInt("ItemLayoutId")
        }
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
                this@ListFragment.observeHandler(viewModel!!.fetchDataResult.get())
            }
        })
    }

    fun observeHandler(newValue: FetchDataResult) {
        if (newValue == FetchDataResult.Success) {
            hideLoading()
            if (viewModel!!.dataArray.count() > 0) {
                listView?.adapter = BaseListAdapter(this, viewModel!!, itemLayoutId!!)
            } else {
                toastShow("Nothing to show here, move along")
            }
        } else if (newValue == FetchDataResult.Failed) {
            hideLoading()
            toastShow("Get data failed")
        }
    }
}