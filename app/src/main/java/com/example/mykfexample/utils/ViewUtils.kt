package com.example.mykfexample.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

/***
 * show progress dialog without text
 */
fun Context?.showProgressDialog() {
    if (this != null) {
        CustomProgressDialog.showProgress(this, "")
    }
}

/***
 * hide progress dialog with text
 */
fun Context?.hideProgressDialog() {
    if(this != null)
        CustomProgressDialog.hideProgress(this)
}

/***
 * show toast message
 */
fun Context.showToast(message: String) {

    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

/**
 * Try to hide the keyboard and returns whether it worked
 */
fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) {
    }
    return false
}