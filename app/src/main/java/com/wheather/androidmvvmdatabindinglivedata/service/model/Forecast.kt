package com.wheather.androidmvvmdatabindinglivedata.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Forecast {

    @SerializedName("forecastday")
    @Expose
    var forecastday: List<Forecastday>? = null

}
