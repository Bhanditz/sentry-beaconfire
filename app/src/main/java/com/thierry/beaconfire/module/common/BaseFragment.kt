package com.thierry.beaconfire.common

import android.app.ProgressDialog
import android.support.annotation.IdRes
import android.support.v4.app.Fragment

/**
 * Created by Thierry on 16/2/19.
 */
abstract class BaseFragment : Fragment() {

    val TAG = this.javaClass.canonicalName
    var dialog: ProgressDialog? = null

    fun showLoading() {
        dialog = ProgressDialog.show(activity, null, "Loading...")
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