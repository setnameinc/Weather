package com.setname.weather.mvp.adaters.welcome

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setname.weather.R
import com.setname.weather.mvp.interfaces.welcome.adapter.list.ListWelcome
import com.setname.weather.mvp.models.adapter.welcome.weather_main.ModelWeatherMain
import kotlinx.android.synthetic.main.adapter_weather_main.view.*

class WelcomeAdapter(private val items: ArrayList<ListWelcome>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        val viewViewHolder:View
        return when(position){

            ListWelcome.ListWelcomeType.WEATHER_UP_PANEL.type -> {
                viewViewHolder = LayoutInflater.from(parent.context).inflate(R.layout.adapter_weather_main, parent, false);
                ViewHolderWeatherUpPanel(viewViewHolder)
            }



            else -> null!!//never happened

        }
    }

    class ViewHolderWeatherUpPanel(private var mView: View) : ViewHolder(mView) {

        override fun bindType(listWelcome:ListWelcome) {

            setWeatherUpPanel(listWelcome as ModelWeatherMain)

        }

        private fun setWeatherUpPanel(modelWeatherMain: ModelWeatherMain){

            mView.apply {
                adapter_image_main_city_name.text = modelWeatherMain.cityName
                adapter_image_main_city_desc.text = modelWeatherMain.descriptor
                adapter_image_main_current_temp.text = modelWeatherMain.currentTemp
            }

        }

    }

    class ViewHolderWeatherDownPanel(private var mView: View) : ViewHolder(mView) {

        override fun bindType(listWelcome:ListWelcome) {



        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val listWelcome:ListWelcome = items[position]
        (holder as ViewHolderWeatherUpPanel).bindType(listWelcome)
    }

    abstract class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        abstract fun bindType(listWelcome:ListWelcome)

    }

    override fun getItemViewType(position: Int): Int = items[position].getListItemType()

    override fun getItemCount(): Int = items.size

}
