package com.wheather.androidmvvmdatabindinglivedata.service.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.internal.LinkedTreeMap

import com.wheather.androidmvvmdatabindinglivedata.network.APIClient
import com.wheather.androidmvvmdatabindinglivedata.network.APIInterface
import com.wheather.androidmvvmdatabindinglivedata.service.model.WhetherForcastResponse
import com.wheather.androidmvvmdatabindinglivedata.utility.Utility

import org.json.JSONObject


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WheatherForcastRepository {

    //Convert Linked tree map data into json object.
    /**
     * this method is use to call on sign up failure response
     * @param t instance of Throwable
     * @author vimlesh narayan September 06, 2017
     */// listener.failure("Something wrong on server. Please try again after some time");
    val data: LiveData<WhetherForcastResponse>
        get() {
            val data = MutableLiveData<WhetherForcastResponse>()
            val apiInterface = APIClient.client.create(APIInterface::class.java)


            val call1 = apiInterface.data
            call1.enqueue(object : Callback<Any> {
                override fun onResponse(call: Call<Any>, response: Response<Any>) {
                    val gson = GsonBuilder().create()
                    Utility.dismissDialog()
                    if (response.isSuccessful) {
                        try {
                            assert(response.body() != null)
                            val linkedTreeMap = response.body() as LinkedTreeMap<String, Any>?
                            val json = JSONObject(linkedTreeMap)
                            val signUpResponse = gson.fromJson(json.toString(), WhetherForcastResponse::class.java)
                            data.value = signUpResponse
                        } catch (e: Exception) {
                            e.printStackTrace()

                        }

                    }
                }

                override fun onFailure(call: Call<Any>, t: Throwable) {
                    call.cancel()
                    Utility.dismissDialog()
                }
            })
            return data
        }

    companion object {
        private var wheatherForcastRepository: WheatherForcastRepository? = null

        val instance: WheatherForcastRepository?
            @Synchronized get() {

                if (wheatherForcastRepository == null) {
                    wheatherForcastRepository = WheatherForcastRepository()
                }
                return wheatherForcastRepository
            }
    }


}
