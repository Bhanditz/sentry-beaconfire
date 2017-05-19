package com.thierry.beaconfire.module.common

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.thierry.beaconfire.BR
import com.thierry.beaconfire.component.ListFragment

/**
 * Created by Thierry on 16/3/12.
 */
class BaseListAdapter(val fragment: ListFragment, val viewModel: BaseListViewModel, val layoutId: Int) : BaseAdapter() {

    var inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(fragment.activity)
    }

    override fun getCount(): Int {
        return viewModel.dataArray.count()
    }

    override fun getItem(position: Int): Any? {
        return viewModel.dataArray[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var mConvertView = convertView
        var binding: ViewDataBinding?
        if (mConvertView == null) {
            binding = DataBindingUtil.inflate(inflater, layoutId, parent, false)
            mConvertView = binding?.root
            mConvertView?.tag = binding
        } else {
            binding = mConvertView.tag as ViewDataBinding
        }
        binding?.setVariable(BR.viewModel, viewModel)
        binding?.setVariable(BR.position, position)
        return mConvertView
    }

}