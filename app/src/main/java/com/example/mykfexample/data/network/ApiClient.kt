package com.example.mykfexample.data.network

import android.annotation.SuppressLint
import com.example.mykfexample.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object ApiClient {

    private val client: Retrofit

        @SuppressLint("HardwareIds")
        get() {
            val gson = GsonBuilder()
                .setLenient()
                .create()
            val builder = Retrofit.Builder()
                .baseUrl("http://www.nactem.ac.uk/software/acromine/")
                .addConverterFactory(GsonConverterFactory.create(gson))

            val client = OkHttpClient.Builder()

            client.connectTimeout(30, TimeUnit.SECONDS)
            client.readTimeout(30, TimeUnit.SECONDS)
            client.writeTimeout(30, TimeUnit.SECONDS)
            // add logging interceptor if DEBUG build
            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
              //loggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                client.addInterceptor(loggingInterceptor)
            }

            client.interceptors().add(Interceptor { chain ->
                val newRequestBuilder = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")

                chain.proceed(newRequestBuilder.build())
            })

            builder.client(client.build())

            return builder.build()
        }


    val service: ApiService
        get() = client.create(ApiService::class.java)


}