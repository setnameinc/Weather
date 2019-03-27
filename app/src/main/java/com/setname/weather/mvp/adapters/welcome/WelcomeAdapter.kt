package com.setname.weather.mvp.adapters.welcome

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setname.weather.R
import com.setname.weather.mvp.adapters.welcome.day.DayAdapter
import com.setname.weather.mvp.adapters.welcome.hour.ThreeHoursAdapter
import com.setname.weather.mvp.interfaces.welcome.adapter.list_main.ListWelcome
import com.setname.weather.mvp.models.adapter.welcome.day.ModelDay
import com.setname.weather.mvp.models.adapter.welcome.hour.ModelThreeHours
import com.setname.weather.mvp.models.adapter.welcome.lists.day.ModelDayList
import com.setname.weather.mvp.models.adapter.welcome.lists.hour.ModelThreeHoursList
import com.setname.weather.mvp.models.database.smart_request.ModelUpPanelFromDB
import com.setname.weather.mvp.utils.poor.AppContext
import kotlinx.android.synthetic.main.adapter_weather_up_panel.view.*
import java.util.logging.Logger

class WelcomeAdapter(private val items: ArrayList<ListWelcome>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val viewViewHolder: View
        return when (position) {

            ListWelcome.ListWelcomeType.WEATHER_UP_PANEL.type -> {
                viewViewHolder =
                    LayoutInflater.from(parent.context).inflate(R.layout.adapter_weather_up_panel, parent, false)

                ViewHolderWeatherUpPanel(viewViewHolder)

            }

            ListWelcome.ListWelcomeType.WEATHER_PER_THREE_HOURS.type -> {
                viewViewHolder =
                    LayoutInflater.from(parent.context).inflate(R.layout.adapter_weather_per_three_hours, parent, false)

                val test = ViewHolderWeatherPerThreeHours(viewViewHolder)

                Logger.getLogger("WelcomeAdapter").info("${test.list.size}")

                return test

            }

            ListWelcome.ListWelcomeType.WEATHER_PER_DAY.type -> {

                viewViewHolder =
                    LayoutInflater.from(parent.context).inflate(R.layout.adapter_weather_per_day, parent, false)

                ViewHolderWeatherPerDay(viewViewHolder)


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

    class ViewHolderWeatherPerThreeHours(private var mView: View) : ViewHolder(mView) {

        lateinit var list: List<ModelThreeHours>

        override fun bindType(listWelcome: ListWelcome) {

            setRv((listWelcome as ModelThreeHoursList))

            list = listWelcome.list

        }

        private fun setRv(modelThreeHoursList: ModelThreeHoursList) {
            val list = arrayListOf<ModelThreeHours>()

            val rv = mView.findViewById<RecyclerView>(R.id.adapter_weather_for_hours_rv)
            val adapter = ThreeHoursAdapter(list)

            rv.layoutManager =
                LinearLayoutManager(AppContext.applicationContext(), LinearLayoutManager.HORIZONTAL, false)
            rv.adapter = adapter

            list.addAll(modelThreeHoursList.list)
            adapter.notifyDataSetChanged()
        }

    }

    class ViewHolderWeatherPerDay(private val mView: View) : ViewHolder(mView) {

        override fun bindType(listWelcome: ListWelcome) {

            val list = mutableListOf<ModelDay>()

            val rv = mView.findViewById<RecyclerView>(R.id.adapter_weather_per_day_rv)
            val adapter = DayAdapter(list)

            rv.layoutManager = LinearLayoutManager(AppContext.applicationContext())
            rv.adapter = adapter

            list.addAll((listWelcome as ModelDayList).list)
            adapter.notifyDataSetChanged()


        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val listWelcome: ListWelcome = items[position]
        when (position) {

            ListWelcome.ListWelcomeType.WEATHER_UP_PANEL.type -> (holder as ViewHolderWeatherUpPanel).bindType(
                listWelcome
            )
            ListWelcome.ListWelcomeType.WEATHER_PER_THREE_HOURS.type -> ((holder as ViewHolderWeatherPerThreeHours).bindType(
                listWelcome
            ))

        }
    }

    abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        abstract fun bindType(listWelcome: ListWelcome)

    }

    override fun getItemViewType(position: Int): Int = items[position].getListItemType()

    override fun getItemCount(): Int = items.size

    fun <T : RecyclerView.ViewHolder> T.listen(event: (type: Int, position: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(getAdapterPosition(), getItemViewType())
        }
        return this
    }

}
