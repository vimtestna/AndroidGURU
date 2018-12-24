package com.wheather.androidmvvmdatabindinglivedata.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class WhetherForcastResponse {

    @SerializedName("location")
    @Expose
    var location: Location? = null
    @SerializedName("current")
    @Expose
    var current: Current? = null
    @SerializedName("forecast")
    @Expose
    var forecast: Forecast? = null

}
