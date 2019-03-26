package com.setname.weather.mvp.adapters.welcome

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setname.weather.R
import com.setname.weather.mvp.adapters.welcome.week.WeekAdapter
import com.setname.weather.mvp.interfaces.welcome.adapter.list_main.ListWelcome
import com.setname.weather.mvp.models.adapter.welcome.weather_weeks.ModelWeatherWeekForList
import com.setname.weather.mvp.models.database.smart_request.ModelUpPanelFromDB
import kotlinx.android.synthetic.main.adapter_weather_up_panel.view.*

class WelcomeAdapter(private val items: ArrayList<ListWelcome>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val viewViewHolder: View
        return when (position) {

            ListWelcome.ListWelcomeType.WEATHER_UP_PANEL.type -> {
                viewViewHolder =
                    LayoutInflater.from(parent.context).inflate(R.layout.adapter_weather_up_panel, parent, false);
                ViewHolderWeatherUpPanel(viewViewHolder)
            }

            ListWelcome.ListWelcomeType.WEATHER_WEEK.type -> {

                viewViewHolder =
                    LayoutInflater.from(parent.context).inflate(R.layout.adapter_weather_week, parent, false);
                ViewHolderWeatherWeek(viewViewHolder)

            }

            else -> null!!//never happened

        }
    }

    class ViewHolderWeatherUpPanel(private var mView: View) : ViewHolder(mView) {

        override fun bindType(listWelcome: ListWelcome) {

            setWeatherUpPanel(listWelcome as ModelUpPanelFromDB)

        }

        private fun setWeatherUpPanel(modelWeatherUpPanel: ModelUpPanelFromDB) {

            mView.apply {
                adapter_image_main_city_name.text = modelWeatherUpPanel.id_city.toString()
                adapter_image_main_city_desc.text = modelWeatherUpPanel.model_up_panel.description
                adapter_image_main_current_temp.text = modelWeatherUpPanel.temp.toString()
            }

        }

    }

    class ViewHolderWeatherWeek(private var mView: View) : ViewHolder(mView) {

        override fun bindType(listWelcome: ListWelcome) {

//            val weekAdapter:WelcomeAdapter = WeekAdapter(listWelcome as ModelWeatherWeekForList)

        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //?
//        val listWelcome: ListWelcome = items[position]
//        (holder as ViewHolderWeatherUpPanel).bindType(listWelcome)
        //?
    }

    abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        abstract fun bindType(listWelcome: ListWelcome)

    }

    override fun getItemViewType(position: Int): Int = items[position].getListItemType()

    override fun getItemCount(): Int = items.size

}
