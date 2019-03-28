package com.setname.weather.mvp.adapters.welcome.hour

import android.support.v7.widget.RecyclerView
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.setname.weather.R
import com.setname.weather.mvp.models.adapter.welcome.hour.ModelThreeHours
import com.setname.weather.mvp.utils.adapters.AdapterClickListener
import com.setname.weather.mvp.utils.expand.listen
import kotlinx.android.synthetic.main.adapter_weather_per_three_hours_model.view.*
import java.util.logging.Logger

class ThreeHoursAdapter(private val list: ArrayList<ModelThreeHours>, private val clickListener: AdapterClickListener) :
    RecyclerView.Adapter<ThreeHoursAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_weather_per_three_hours_model,
                parent,
                false
            )
        ).listen { pos, type ->
            run {
                clickListener.setUpPanel(
                    id_city = list[pos].id_city,
                    id_dt = list[pos].id_dt
                );
            }
        }
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.setModel(list[p1])
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun setModel(modelHourForModelDay: ModelThreeHours) {

            view.apply {

                adapter_weather_per_three_hours_model_image.setImage(modelHourForModelDay.image_url)
                adapter_weather_per_three_hours_model_temp.setTemp(modelHourForModelDay.temp)
                adapter_weather_per_three_hours_model_time.setTime(modelHourForModelDay.id_dt)

            }

        }

        private fun ImageView.setImage(image_url: String) {

            Glide.with(itemView.context).load("http://openweathermap.org/img/w/$image_url.png").into(this)

        }

        private fun TextView.setTemp(temp: Float) {

            this.text = temp.toString()

        }

        private fun TextView.setTime(dt: Long) {

            //check if it's now or not
            if (System.currentTimeMillis()/1000 > dt){

                this.text = "Now"

            } else {

                //(dt* 1000L) convert sec into millisec

                this.text = DateFormat.format("H a", dt * 1000L)

            }


        }

    }

}