package com.thierry.beaconfire.common

import android.app.ProgressDialog
import android.content.Intent
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.content.LocalBroadcastManager
import android.util.Log
import android.widget.Toast
import com.thierry.beaconfire.util.Constants

/**
 * Created by Thierry on 16/2/19.
 */
abstract class BaseFragment : Fragment() {

    val TAG = this.javaClass.canonicalName
    var dialog: ProgressDialog? = null

    fun showLoading() {
        Log.d(TAG, "showLoading")
        if (activity != null) {
            dialog = ProgressDialog.show(activity, null, "Loading...")
        }
    }

    fun hideLoading() {
        try {
            if (dialog != null && dialog!!.isShowing) {
                dialog?.dismiss()
            }
        } catch(e: Exception) {
        }
    }

    fun toastShow(message: String, duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(activity, message, duration).show()
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
        transaction.commitAllowingStateLoss()
        fragmentManager.executePendingTransactions()
    }

}