package com.setname.weather.mvp.adapters.welcome.day

import android.support.v7.widget.RecyclerView
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.setname.weather.R
import com.setname.weather.mvp.models.adapter.welcome.day.ModelDay
import com.setname.weather.mvp.utils.adapters.AdapterClickListener
import com.setname.weather.mvp.utils.expand.listen
import kotlinx.android.synthetic.main.adapter_weather_per_day_model.view.*
import java.util.logging.Logger

class DayAdapter(private val list: List<ModelDay>, private val clickListener: AdapterClickListener) :
    RecyclerView.Adapter<DayAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_weather_per_day_model,
                parent,
                false
            )
        ).listen { pos, type ->
            run {
                clickListener.setThreeHoursPanel(
                    id_city = list[pos].id_city,
                    id_dt = list[pos].id_dt
                )
                clickListener.setUpPanel(
                    id_city = list[pos].id_city,
                    id_dt = list[pos].id_dt
                )
            }
        }
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.setModel(list[p1])
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun setModel(modelHourForModelDay: ModelDay) {

            view.apply {

                adapter_weather_per_day_model_time.setTime(modelHourForModelDay.id_dt)
                adapter_weather_per_day_model_image.setImage(modelHourForModelDay.image_url)
                adapter_weather_per_day_model_temp.setTemp(modelHourForModelDay.temp)

            }

        }

        private fun ImageView.setImage(image_url: String) {

            Glide.with(itemView.context).load("http://openweathermap.org/img/w/$image_url.png").into(this)

        }

        private fun TextView.setTemp(temp: Float) {

            this.text = temp.toString()

        }

        private fun TextView.setTime(dt: Long) {

            this.text = DateFormat.format("EEEE", dt*1000).toString()

        }

    }

}

