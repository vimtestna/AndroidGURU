package com.wheather.androidmvvmdatabindinglivedata.view.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import com.wheather.androidmvvmdatabindinglivedata.R
import com.wheather.androidmvvmdatabindinglivedata.databinding.ActivityMainBinding
import com.wheather.androidmvvmdatabindinglivedata.service.model.Current
import com.wheather.androidmvvmdatabindinglivedata.service.model.Forecastday
import com.wheather.androidmvvmdatabindinglivedata.service.model.Location
import com.wheather.androidmvvmdatabindinglivedata.service.model.WhetherForcastResponse
import com.wheather.androidmvvmdatabindinglivedata.utility.Utility
import com.wheather.androidmvvmdatabindinglivedata.view.adapter.WheatherAdapter

import com.wheather.androidmvvmdatabindinglivedata.viewmodel.ProjectListViewModel


class MainActivity : AppCompatActivity() {
    private var wheatherAdapter: WheatherAdapter? = null
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        wheatherAdapter = WheatherAdapter()
        binding!!.tempList.adapter = wheatherAdapter
        if (Utility.isConnectedToInternet(this)) {
            val loginViewModel = ViewModelProviders.of(this).get(ProjectListViewModel::class.java)
            observeViewModel(loginViewModel)
        }
    }

    private fun observeViewModel(viewModel: ProjectListViewModel) {
        // Update the list when the data changes
        viewModel.projectListObservable.observe(this, Observer { projects ->
            if (projects != null) {
                val current = projects.current
                val location = projects.location
                if (current != null) {

                    val forecastdayList = projects.forecast!!.forecastday
                    binding!!.isLoading = current
                    binding!!.isLocation = location
                    wheatherAdapter!!.setProjectList(forecastdayList!!)
                }

            }
        })
    }


}
