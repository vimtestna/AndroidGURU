package com.wheather.androidmvvmdatabindinglivedata.network


import com.wheather.androidmvvmdatabindinglivedata.constant.AppConstants

import retrofit2.Call
import retrofit2.http.GET


interface APIInterface {
    @get:GET(AppConstants.forcast)
    val data: Call<Any>

}
