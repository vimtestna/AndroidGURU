package com.wheather.androidmvvmdatabindinglivedata.view.adapter

import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup


import com.wheather.androidmvvmdatabindinglivedata.R
import com.wheather.androidmvvmdatabindinglivedata.databinding.ProjectListItemBinding
import com.wheather.androidmvvmdatabindinglivedata.service.model.Forecastday
import java.util.Objects


class WheatherAdapter : RecyclerView.Adapter<WheatherAdapter.ProjectViewHolder>() {

    private var forecastdays: List<Forecastday>? = null

    fun setProjectList(forecastdays: List<Forecastday>) {
        if (this.forecastdays == null) {
            this.forecastdays = forecastdays
            notifyItemRangeInserted(0, forecastdays.size)
        } else {
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return this@WheatherAdapter.forecastdays!!.size
                }

                override fun getNewListSize(): Int {
                    return forecastdays.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

                    return this@WheatherAdapter.forecastdays!![oldItemPosition].day?.avgtempF === forecastdays[newItemPosition].day?.avgtempF
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val project = forecastdays[newItemPosition]
                    val old = forecastdays[oldItemPosition]
                    return project.day?.avgtempF === old.day?.avgtempF && project.day?.maxtempF == old.day?.maxtempF
                }
            })
            this.forecastdays = forecastdays
            result.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val binding = DataBindingUtil
                .inflate<ProjectListItemBinding>(LayoutInflater.from(parent.context), R.layout.project_list_item,
                        parent, false)



        return ProjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.binding.forcastday = forecastdays!![position]
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return if (forecastdays == null) 0 else forecastdays!!.size
    }

     class ProjectViewHolder(val binding: ProjectListItemBinding) : RecyclerView.ViewHolder(binding.root)
}
