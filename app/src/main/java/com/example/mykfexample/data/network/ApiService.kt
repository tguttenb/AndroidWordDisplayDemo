package com.example.mykfexample.data.network


import com.example.mykfexample.models.ResponseItem
import retrofit2.Response
import retrofit2.http.*

interface ApiService {


    @GET("dictionary.py?")
    suspend fun lfResponse(@Query("sf") sf:String): Response<ArrayList<ResponseItem>>

}