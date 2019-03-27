package com.setname.weather.mvp.adapters.welcome.day

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setname.weather.R
import com.setname.weather.mvp.models.adapter.welcome.day.ModelDay

class DayAdapter(private val list: List<ModelDay>):
    RecyclerView.Adapter<DayAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return (ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_weather_per_day_model,
                parent,
                false
            )
        ))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.setModel(list[p1])
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun setModel(modelHourForModelDay: ModelDay) {

            view.apply {


            }

        }

    }

}

