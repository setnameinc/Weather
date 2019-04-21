package com.setname.weather.mvp.adapters.welcome.hour

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.setname.weather.R
import com.setname.weather.mvp.models.adapter.welcome.hour.ModelThreeHours
import com.setname.weather.mvp.utils.adapters.AdapterClickListener
import com.setname.weather.mvp.utils.adapters.Selector
import com.setname.weather.mvp.utils.poor.AppContext
import kotlinx.android.synthetic.main.adapter_weather_per_three_hours_model.view.*

class ThreeHoursAdapter(
    private val list: ArrayList<ModelThreeHours>,
    private val clickListener: AdapterClickListener
) :
    RecyclerView.Adapter<ThreeHoursAdapter.ViewHolder>() {

    private var currentPos = 0

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_weather_per_three_hours_model,
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(viewHolder: ViewHolder, pos: Int) {

        viewHolder.setModel(list[pos])

        if (pos == currentPos) {

            /*CoroutineScope(Dispatchers.Main).launch {

                withContext(Dispatchers.Main){

                    clickListener.setUpPanel(
                        id_city = list[pos].id_city,
                        id_dt = list[pos].id_dt
                    )

                }

                withContext(Dispatchers.Main){

                    drawSelector(viewHolder)

                }

            }*/

            clickListener.setUpPanel(
                id_city = list[pos].id_city,
                id_dt = list[pos].id_dt
            )

            drawSelector(viewHolder)

        }

    }

    private var viewSelector: Selector = Selector(AppContext.applicationContext());

    private fun drawSelector(viewHolder: ViewHolder) {

        viewHolder.view.apply {

            if (viewSelector.parent != null) {
                (viewSelector.parent as ViewGroup).removeView(viewSelector)
            }

            adapter_weather_per_three_hours_model_const_layout.addView(viewSelector)

        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view),
        View.OnClickListener {

        init {

            view.setOnClickListener(this)

        }

        override fun onClick(v: View?) {

            if (currentPos != adapterPosition) {

                currentPos = adapterPosition

                notifyItemChanged(adapterPosition)

            }

        }

        fun setModel(modelHourForModelDay: ModelThreeHours) {

            view.apply {

                adapter_weather_per_three_hours_model_image.setImage(modelHourForModelDay.image_url)
                adapter_weather_per_three_hours_model_temp.setTemp(modelHourForModelDay.temp)
                adapter_weather_per_three_hours_model_time.setTime(modelHourForModelDay.id_dt)

            }

        }

        private fun ImageView.setImage(image_url: String) {

            when (image_url) {

                "01d" -> Glide.with(itemView.context).load(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.sun
                    )
                ).into(this)
                "01n" -> Glide.with(itemView.context).load(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.moon_sun
                    )
                ).into(this)
                else -> Glide.with(itemView.context).load("http://openweathermap.org/img/w/$image_url.png").into(this)

            }

        }

        private fun TextView.setTemp(temp: Float) {

            this.text = "${Math.round(temp)}"

        }

        private fun TextView.setTime(dt: Long) {

            //check if it's now or not
            if (System.currentTimeMillis() / 1000 > dt) {

                this.text = "Now"

            } else {

                //(dt* 1000L) convert sec into millisec

                this.text = DateFormat.format("H a", dt * 1000L)

            }


        }

    }

}