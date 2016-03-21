package com.thierry.beaconfire.common

import android.app.ProgressDialog
import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.util.Log
import android.widget.Toast

/**
 * Created by Thierry on 16/2/19.
 */
abstract class BaseActivity : FragmentActivity() {

    val TAG = this.javaClass.canonicalName
    var dialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun showLoading() {
        if (this != null) {
            dialog = ProgressDialog.show(this, null, "Loading...")
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
        Toast.makeText(this, message, duration).show()
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
        Log.d(TAG, "tag" + tag)
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(layoutId, fragment, tag)
        transaction.commitAllowingStateLoss()
        fragmentManager.executePendingTransactions()
    }
}
