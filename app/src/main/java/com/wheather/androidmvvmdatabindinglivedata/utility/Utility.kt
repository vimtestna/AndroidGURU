package com.wheather.androidmvvmdatabindinglivedata.utility

import android.app.ProgressDialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo


object Utility {

    //captured picture uri


    private var mProgressDialog: ProgressDialog? = null


    fun isConnectedToInternet(context: Context): Boolean {
        try {
            val connectivity = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivity != null) {
                val info = connectivity.allNetworkInfo
                if (info != null) {
                    for (anInfo in info) {
                        if (anInfo.state == NetworkInfo.State.CONNECTED) {
                            return true
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return false
    }


    fun showProgressDialog(msg: String, ctx: Context) {
        try {
            if (mProgressDialog == null) {

                mProgressDialog = ProgressDialog(ctx)
                mProgressDialog!!.setMessage(msg)
                mProgressDialog!!.isIndeterminate = true
                mProgressDialog!!.setCancelable(false)
                mProgressDialog!!.show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    fun dismissDialog() {

        try {
            if (mProgressDialog != null) {
                mProgressDialog!!.dismiss()
                mProgressDialog = null
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


}