package com.wheather.androidmvvmdatabindinglivedata.network


import com.wheather.androidmvvmdatabindinglivedata.constant.AppConstants

import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object APIClient {


    // created interceptor to log api header and body
    // return retrofit client
    val client: Retrofit
        get() {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client: OkHttpClient
            client = OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS).addInterceptor(interceptor).build()
            return Retrofit.Builder()
                    .baseUrl(AppConstants.Base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
        }
}
