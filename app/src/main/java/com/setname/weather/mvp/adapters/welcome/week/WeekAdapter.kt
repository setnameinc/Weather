package com.setname.weather.mvp.adapters.welcome.week

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.setname.weather.R
import com.setname.weather.mvp.interfaces.welcome.adapter.list_additional.ListWelcomeWeek
import com.setname.weather.mvp.models.adapter.welcome.weather_weeks.day.hour.ModelHourForModelDay
import kotlinx.android.synthetic.main.adapter_weather_week_per_hours_model.view.*

class WeekAdapter(private val items: ArrayList<ListWelcomeWeek>) :
    RecyclerView.Adapter<WeekAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val viewViewHolder: View
        return when (position) {

            ListWelcomeWeek.ListWelcomeWeekType.PER_THREE_HOURS.type -> {
                viewViewHolder =
                    LayoutInflater.from(parent.context).inflate(R.layout.adapter_weather_up_panel, parent, false);
                ViewHolderWeatherWeekHours(viewViewHolder)
            }

            ListWelcomeWeek.ListWelcomeWeekType.PER_DAY.type -> {

                viewViewHolder =
                    LayoutInflater.from(parent.context).inflate(R.layout.adapter_weather_week, parent, false);
                ViewHolderWeatherWeekDay(viewViewHolder)

            }

            else -> null!!//never happened

        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, pos: Int) {



    }

    abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        abstract fun bindType(listWelcome: ListWelcomeWeek)

    }

    class ViewHolderWeatherWeekHours(private val view1: View) : ViewHolder(view1) {
        override fun bindType(listWelcome: ListWelcomeWeek) {
            setData(listWelcome as ModelHourForModelDay)
        }

        fun setData(modelHourForModelDay:ModelHourForModelDay){

            setImage(modelHourForModelDay.image_url)
            setTemp(modelHourForModelDay.temp)
            setTime(modelHourForModelDay.dt)

        }

        private fun setTime(dt: Long) {

            view1.adapter_weather_week_for_hours_model_temp.text = dt.toString()

        }

        private fun setTemp(temp: Float) {

            view1.adapter_weather_week_for_hours_model_temp.text = temp.toString()

        }

        private fun setImage(image_url:String){

            Glide.with(itemView.context).load(image_url).into(view1.adapter_weather_week_for_hours_model_image)

        }

    }

    class ViewHolderWeatherWeekDay(private val view1: View) : ViewHolder(view1){
        override fun bindType(listWelcome: ListWelcomeWeek) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    }

    override fun getItemViewType(position: Int): Int = items[position].getListItemType()



}