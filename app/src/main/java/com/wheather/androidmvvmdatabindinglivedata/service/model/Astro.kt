package com.wheather.androidmvvmdatabindinglivedata.service.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Astro {

    @SerializedName("sunrise")
    @Expose
    var sunrise: String? = null
    @SerializedName("sunset")
    @Expose
    var sunset: String? = null
    @SerializedName("moonrise")
    @Expose
    var moonrise: String? = null
    @SerializedName("moonset")
    @Expose
    var moonset: String? = null

}
