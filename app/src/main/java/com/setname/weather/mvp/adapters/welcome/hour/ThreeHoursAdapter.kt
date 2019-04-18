package com.setname.weather.mvp.adapters.welcome.hour

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

    class StateOfListeners(){

        var prevPos = -2

    }

    private val states = StateOfListeners()

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {

        val viewHolder = ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.adapter_weather_per_three_hours_model,
                parent,
                false
            ), clickListener,
            states
        )

        return viewHolder
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(viewHolder: ViewHolder, p1: Int) {

        viewHolder.setModel(list[p1])

        /*viewHolder.listen { pos, type ->
            run {

                clickListener.setUpPanel(
                    id_city = list[pos].id_city,
                    id_dt = list[pos].id_dt
                )

            }
        }*/

    }

    class ViewHolder(val view: View, val clickListener: AdapterClickListener, val states:StateOfListeners) : RecyclerView.ViewHolder(view),
        View.OnClickListener {

        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            val curPos = adapterPosition
            val oldPos = states.prevPos

            if (curPos != oldPos) {
                
                if (oldPos >= 0) {

                    removeSelector()
                    clickListener.updateAdapter(curPos)

                }

                drawSelector()
                clickListener.updateAdapter(oldPos)

                Log.i("ThreeHA", "cur = ${curPos}, old = ${states.prevPos}")

                states.prevPos = curPos
            }

        }

        private var viewSelector: Selector? = null

        fun removeSelector() {
            Log.i("ThreeHA", "Removed")
            if (viewSelector != null ) {
                view.apply {
                    adapter_weather_per_three_hours_model_const_layout.removeView(viewSelector)
                }
            }
        }

        fun drawSelector() {
            viewSelector = Selector(AppContext.applicationContext())
            val drawViewLayoutParams =
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            viewSelector?.layoutParams = drawViewLayoutParams
            view.apply {
                adapter_weather_per_three_hours_model_const_layout.addView(viewSelector)
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

            Glide.with(itemView.context).load("http://openweathermap.org/img/w/$image_url.png").into(this)

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