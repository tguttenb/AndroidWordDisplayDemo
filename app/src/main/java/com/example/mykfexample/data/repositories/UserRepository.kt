package com.example.mykfexample.data.repositories

import android.app.Activity
import android.util.Log
import com.example.mykfexample.data.network.ApiClient
import com.example.mykfexample.utils.hideProgressDialog
import com.example.mykfexample.utils.showToast
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

@Suppress("UNCHECKED_CAST")
class UserRepository {

    suspend fun callApi(
        activity: Activity,
        isFrom:Int,
        id:String,
    ): Response<Any>? {

        try {

            when (isFrom) {
                1 -> {
                    val data = ApiClient.service.lfResponse(id)
                    return data as Response<Any>

                }

            }
        } catch (e: Exception) {
            activity.hideProgressDialog()
            Log.e("Exception",e.toString())
            when (e) {
                is ConnectException, is UnknownHostException ->
                    showToast(activity,"Internet connection is not available. Please try again!")
                is IOException, is TimeoutException ->{
                    showToast(activity,"There is some server error. Please try again later.")
                }

            }
        }
        return null

    }


}

    private fun showToast(activity: Activity, data:String)

    {
        try {
            activity.showToast(data )
            activity.hideProgressDialog()
        } catch (e: Exception) {
        }
    }


