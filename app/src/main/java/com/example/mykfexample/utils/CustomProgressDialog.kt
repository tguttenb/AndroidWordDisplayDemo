package com.example.mykfexample.utils

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.PorterDuff
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.TextView

import com.example.mykfexample.R

object CustomProgressDialog {
    private var mDialog: Dialog? = null

    fun showProgress(context: Context?, text: String) {

        if (mDialog == null) {
            mDialog = Dialog(context!!)
            mDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            mDialog!!.setContentView(R.layout.dialog_progress)
            val mProgressBar =
                mDialog!!.findViewById<ProgressBar>(R.id.progress_bar)
            val textView =
                mDialog!!.findViewById<TextView>(R.id.textViewTitle)
            if (text.isNotEmpty()) {
                textView.visibility = View.VISIBLE
                textView.text = text
            } else {
                textView.visibility = View.GONE
            }
//            mProgressBar.indeterminateDrawable.setColorFilter(
//                ContextCompat.getColor(context, android.R.color.black),
//                PorterDuff.Mode.SRC_IN
//            )
            mProgressBar.visibility = View.VISIBLE
            // you can change or add this line according to your need
            mProgressBar.isIndeterminate = true
            mDialog!!.setCancelable(false)
            mDialog!!.setCanceledOnTouchOutside(false)
            mDialog!!.show()
        }
    }

    fun updateProgress(text: String) {
        if (mDialog != null && mDialog!!.isShowing) {
            val textView =
                mDialog!!.findViewById<TextView>(R.id.textViewTitle)
            textView.text = text
        }
    }

    fun hideProgress(context: Context?) {
        try {
            if (mDialog != null && mDialog!!.isShowing && !(context as Activity).isFinishing) {
                mDialog!!.dismiss()
                mDialog = null
            }
        }catch ( e: IllegalArgumentException) {
            // Handle or log or ignore
        } catch ( e:Exception ) {
            // Handle or log or ignore
        } finally {
            mDialog = null
        }
    }


}