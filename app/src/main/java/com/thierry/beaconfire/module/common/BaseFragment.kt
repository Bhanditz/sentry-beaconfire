package com.thierry.beaconfire.common

import android.app.ProgressDialog
import android.os.Bundle
import android.support.annotation.IdRes
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Thierry on 16/2/19.
 */
abstract class BaseFragment : Fragment() {

    val TAG = this.javaClass.canonicalName
    var dialog: ProgressDialog? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    fun showLoading() {
        dialog = ProgressDialog.show(activity, null, "加载中，请稍候...")
    }

    fun hideLoading() {
        dialog?.dismiss()
    }

    fun toast(message: String) {
        toast(message)
    }

    /**
     * replaceFragment

     * @param layoutId
     * *
     * @param fragment
     * *
     * @param tag
     */
    fun replaceFragmentByTag(@IdRes layoutId: Int, fragment: Fragment, tag: String) {
        val fragmentManager = childFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(layoutId, fragment, tag)
        transaction.addToBackStack(null)
        transaction.commitAllowingStateLoss()
        fragmentManager.executePendingTransactions()
    }

}