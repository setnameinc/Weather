package com.setname.weather.mvp.adapters.welcome.day

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.setname.weather.R
import com.setname.weather.mvp.models.adapter.welcome.day.ModelDay
import com.setname.weather.mvp.utils.adapters.AdapterClickListener
import com.setname.weather.mvp.utils.adapters.Selector
import com.setname.weather.mvp.utils.poor.AppContext
import kotlinx.android.synthetic.main.adapter_weather_per_day_model.view.*
import java.text.SimpleDateFormat
import java.util.*

class DayAdapter(private val list: List<ModelDay>, private val clickListener: AdapterClickListener) :
    RecyclerView.Adapter<DayAdapter.ViewHolder>() {

    private var currentPos = 0

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_weather_per_day_model,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(viewHolder: ViewHolder, pos: Int) {

        viewHolder.setModel(list[pos])



        if (pos == currentPos) {

            drawSelector(viewHolder)

            clickListener.setThreeHoursPanel(
                id_city = list[pos].id_city,
                id_dt = list[pos].id_dt - 64800L
                /*(-64800L) 3am - (15 hours(from middle forecast to start day)
                 + 3 hours(UTC or openweatherforecast feature)) = 0pm*/
            )
            clickListener.setUpPanel(
                id_city = list[pos].id_city,
                id_dt = list[pos].id_dt
            )

        }

    }

    private var viewSelector: Selector = Selector(AppContext.applicationContext())

    private fun drawSelector(viewHolder: ViewHolder) {

        viewHolder.view.apply {

            if (viewSelector.parent != null) {
                (viewSelector.parent as ViewGroup).removeView(viewSelector)
            }

            adapter_weather_per_day_model_const_layout.addView(viewSelector)

        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        init {

            view.setOnClickListener(this)

        }

        override fun onClick(v: View?) {

            if (currentPos != adapterPosition) {

                currentPos = adapterPosition

                notifyItemChanged(adapterPosition)

            }

        }

        fun setModel(modelHourForModelDay: ModelDay) {

            view.apply {

                adapter_weather_per_day_model_time.setTime(modelHourForModelDay.id_dt)
                adapter_weather_per_day_model_image.setImage(modelHourForModelDay.image_url)
                adapter_weather_per_day_model_temp.setTemp(modelHourForModelDay.temp)

            }

        }

        private fun ImageView.setImage(image_url: String) {

            if (image_url == "01d") {

                Glide.with(itemView.context).load(ContextCompat.getDrawable(context, R.drawable.sun)).into(this)

            } else {

                Glide.with(itemView.context).load("http://openweathermap.org/img/w/$image_url.png").into(this)

            }

        }

        private fun TextView.setTemp(temp: Float) {

            this.text = "${Math.round(temp)}"

        }

        private fun TextView.setTime(dt: Long) {

            //(dt* 1000L) convert sec into millisec

            this.text = SimpleDateFormat("EEEE", Locale.ENGLISH).format(dt * 1000).toString()

        }

        private fun drawSelector() {


        }

    }

}

