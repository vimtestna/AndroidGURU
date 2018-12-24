package com.wheather.androidmvvmdatabindinglivedata.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData


import com.wheather.androidmvvmdatabindinglivedata.service.model.WhetherForcastResponse
import com.wheather.androidmvvmdatabindinglivedata.service.repository.WheatherForcastRepository

class ProjectListViewModel(application: Application) : AndroidViewModel(application) {
    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    val projectListObservable: LiveData<WhetherForcastResponse>

    init {

        // If any transformation is needed, this can be simply done by Transformations class ...
        projectListObservable = WheatherForcastRepository.instance!!.data
    }
}
