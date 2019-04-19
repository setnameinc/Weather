package com.setname.weather.mvp.adapters.welcome

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setname.weather.R
import com.setname.weather.mvp.adapters.welcome.day.DayAdapter
import com.setname.weather.mvp.adapters.welcome.hour.ThreeHoursAdapter
import com.setname.weather.mvp.adapters.welcome.uppanel.UpPanelViewPagerAdapter
import com.setname.weather.mvp.interfaces.welcome.adapters.list_main.ListWelcome
import com.setname.weather.mvp.models.adapter.welcome.day.ModelDay
import com.setname.weather.mvp.models.adapter.welcome.hour.ModelThreeHours
import com.setname.weather.mvp.models.adapter.welcome.lists.day.ModelDayList
import com.setname.weather.mvp.models.adapter.welcome.lists.hour.ModelThreeHoursList
import com.setname.weather.mvp.models.database.weather.smart_request.ModelUpPanel
import com.setname.weather.mvp.presenters.welcome.WelcomePresenter
import com.setname.weather.mvp.utils.adapters.AdapterClickListener
import com.setname.weather.mvp.utils.poor.AppContext
import kotlinx.android.synthetic.main.adapter_weather_up_panel.view.*
import java.util.logging.Logger

class WelcomeAdapter(private val items: ArrayList<ListWelcome>, private val welcomePresenter: WelcomePresenter) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    AdapterClickListener {

    private lateinit var viewHolderUpPanel: ViewHolderWeatherUpPanel

    private lateinit var viewHolderWeatherPerThreeHours: ViewHolderWeatherPerThreeHours

    override fun setThreeHoursPanel(id_city: Long, id_dt: Long) {

        viewHolderWeatherPerThreeHours.addToList(
            ModelThreeHoursList(
                welcomePresenter.getThreeHours(id_dt = id_dt, id_city = id_city)
            )
        )

    }

    override fun setUpPanel(id_city: Long, id_dt: Long) {

        viewHolderUpPanel.updateWeatherUpPanel(

            welcomePresenter.getUpPanel(id_city = id_city, id_dt = id_dt)

        )

    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val viewViewHolder: View
        return when (position) {

            ListWelcome.ListWelcomeType.WEATHER_UP_PANEL.type -> {
                viewViewHolder =
                    LayoutInflater.from(parent.context).inflate(R.layout.adapter_weather_up_panel, parent, false)

                ViewHolderWeatherUpPanel(viewViewHolder, welcomePresenter)

            }

            ListWelcome.ListWelcomeType.WEATHER_PER_THREE_HOURS.type -> {
                viewViewHolder =
                    LayoutInflater.from(parent.context).inflate(R.layout.adapter_weather_per_three_hours, parent, false)

                ViewHolderWeatherPerThreeHours(viewViewHolder)

            }

            ListWelcome.ListWelcomeType.WEATHER_PER_DAY.type -> {

                viewViewHolder =
                    LayoutInflater.from(parent.context).inflate(R.layout.adapter_weather_per_day, parent, false)

                ViewHolderWeatherPerDay(viewViewHolder)


            }

            else -> null!!//never happened

        }
    }

    private class ViewHolderWeatherUpPanel(private val mView: View, private val welcomePresenter: WelcomePresenter) :
        ViewHolder(mView) {

        private lateinit var upPanelViewPagerAdapter:UpPanelViewPagerAdapter

        override fun bindType(listWelcome: ListWelcome, adapterClickListener: AdapterClickListener) {

            setWeatherUpPanel(listWelcome as ModelUpPanel)

        }

        fun setWeatherUpPanel(modelWeatherUpPanel: ModelUpPanel){

            upPanelViewPagerAdapter = UpPanelViewPagerAdapter(welcomePresenter.getFragmentManager(), modelWeatherUpPanel)

            mView.apply {

                adapter_weather_up_panel_view_pager.adapter =
                    upPanelViewPagerAdapter

                if (adapter_image_main_city_name.text != welcomePresenter.getPlace(modelWeatherUpPanel.id_city).name_city) {
                    adapter_image_main_city_name.text = welcomePresenter.getPlace(modelWeatherUpPanel.id_city).name_city
                }

            }

        }

        fun updateWeatherUpPanel(modelWeatherUpPanel: ModelUpPanel) {

            mView.apply {

                upPanelViewPagerAdapter.update(modelWeatherUpPanel)

                if (adapter_image_main_city_name.text != welcomePresenter.getPlace(modelWeatherUpPanel.id_city).name_city) {
                    adapter_image_main_city_name.text = welcomePresenter.getPlace(modelWeatherUpPanel.id_city).name_city
                }

                Logger.getLogger("Welcome").info("${modelWeatherUpPanel.temp}")

            }

        }

    }

    private class ViewHolderWeatherPerThreeHours(private var mView: View) : ViewHolder(mView) {

        private var listForAdapter = arrayListOf<ModelThreeHours>()
        private lateinit var adapter: ThreeHoursAdapter

        override fun bindType(listWelcome: ListWelcome, adapterClickListener: AdapterClickListener) {

            setRv((listWelcome as ModelThreeHoursList), adapterClickListener)

        }

        private fun setRv(modelThreeHoursList: ModelThreeHoursList, adapterClickListener: AdapterClickListener) {

            val rv = mView.findViewById<RecyclerView>(R.id.adapter_weather_for_hours_rv)
            adapter = ThreeHoursAdapter(listForAdapter, adapterClickListener)

            rv.layoutManager =
                LinearLayoutManager(AppContext.applicationContext(), LinearLayoutManager.HORIZONTAL, false)
            rv.adapter = adapter

            addToList(modelThreeHoursList)


        }

        fun addToList(modelThreeHoursList: ModelThreeHoursList) {

            listForAdapter.clear()
            listForAdapter.addAll(modelThreeHoursList.list)

            adapter.notifyDataSetChanged()

        }

    }

    private class ViewHolderWeatherPerDay(private val mView: View) : ViewHolder(mView) {

        override fun bindType(listWelcome: ListWelcome, adapterClickListener: AdapterClickListener) {

            setRv(listWelcome, adapterClickListener)

        }

        private fun setRv(listWelcome: ListWelcome, adapterClickListener: AdapterClickListener) {
            val list = mutableListOf<ModelDay>()

            val rv = mView.findViewById<RecyclerView>(R.id.adapter_weather_per_day_rv)
            val adapter = DayAdapter(list, adapterClickListener)

            rv.layoutManager = LinearLayoutManager(AppContext.applicationContext())
            rv.adapter = adapter

            list.addAll((listWelcome as ModelDayList).list)
            adapter.notifyDataSetChanged()
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val listWelcome: ListWelcome = items[position]
        when (position) {

            ListWelcome.ListWelcomeType.WEATHER_UP_PANEL.type -> {

                viewHolderUpPanel = (holder as ViewHolderWeatherUpPanel)

                viewHolderUpPanel.bindType(
                    listWelcome,
                    this
                )

            }
            ListWelcome.ListWelcomeType.WEATHER_PER_THREE_HOURS.type -> {

                viewHolderWeatherPerThreeHours = (holder as ViewHolderWeatherPerThreeHours)

                viewHolderWeatherPerThreeHours.bindType(
                    listWelcome,
                    this
                )
            }
            ListWelcome.ListWelcomeType.WEATHER_PER_DAY.type -> {

                (holder as ViewHolderWeatherPerDay).bindType(
                    listWelcome,
                    this
                )

            }

        }
    }

    abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        abstract fun bindType(listWelcome: ListWelcome, adapterClickListener: AdapterClickListener)

    }

    override fun getItemViewType(position: Int): Int = items[position].getListItemType()

    override fun getItemCount(): Int = items.size
}
