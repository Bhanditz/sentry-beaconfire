package com.thierry.beaconfire.module.dashboard

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.thierry.beaconfire.R
import com.thierry.beaconfire.databinding.ListitemDashboardBinding

/**
 * Created by Thierry on 16/3/12.
 */
class DashboardAdapter(val context: Context, val viewModel: DashboardViewModel) : BaseAdapter() {

    var inflater: LayoutInflater

    init {
        inflater = LayoutInflater.from(context);
    }

    override fun getCount(): Int {
        return viewModel.dataArray.count()
    }

    override fun getItem(position: Int): Any? {
        return viewModel.dataArray[position]
    }

    override fun getItemId(position: Int): Long {
        return position as Long;
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var mConvertView = convertView
        var binding: ListitemDashboardBinding?
        if (mConvertView == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.listitem_dashboard, parent, false)
            mConvertView = binding!!.root;
            mConvertView.tag = binding
        } else {
            binding = mConvertView.tag as ListitemDashboardBinding
        }
        binding!!.viewModel = viewModel
        binding!!.position = position
        return mConvertView
    }


    //    @Override
    //    public View getView(int position, View convertView, ViewGroup parent)
    //    {
    //        if (convertView == null) {
    //            binding = DataBindingUtil.inflate(inflater, R.layout.itemm, parent, false);
    //            convertView = binding.getRoot();
    //            convertView.setTag(binding);
    //        } else {
    //            binding = (ItemmBinding) convertView.getTag();
    //        }
    //        binding.setVariable(BR.item, mlist.get(position));
    //        binding.setAdapter(this);
    //        return convertView;
    //    }
    //    public void setOnclickListener(View.OnClickListener itemClickListener)
    //    {
    //        this.itemClickListener = itemClickListener;
    //    }
}