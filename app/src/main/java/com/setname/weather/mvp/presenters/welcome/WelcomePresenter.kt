package com.setname.weather.mvp.presenters.welcome

import android.support.v4.app.FragmentManager
import com.setname.weather.mvp.interfaces.welcome.WelcomeView
import com.setname.weather.mvp.interfaces.welcome.adapters.up_panel.view_pager.InteractionWithWelcomePresenterFromViewPager
import com.setname.weather.mvp.models.adapter.welcome.lists.day.ModelDayList
import com.setname.weather.mvp.models.adapter.welcome.lists.hour.ModelThreeHoursList
import com.setname.weather.mvp.models.database.weather.ModelWeatherForDB
import com.setname.weather.mvp.models.retrofit.ModelResponse
import com.setname.weather.mvp.presenters.welcome.with_db.InteractionsWithDatabase
import com.setname.weather.mvp.retrofit.WeatherAPIService
import com.setname.weather.mvp.utils.converters.ConverterResponseToDBType
import com.setname.weather.mvp.utils.poor.AppContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Logger

class WelcomePresenter(private var welcomeView: WelcomeView): InteractionWithWelcomePresenterFromViewPager {

    private val THREE_HOURS_IN_MS = 10800000L

    private val weatherAPIService by lazy {
        WeatherAPIService.retrofit
    }

    private val log by lazy {
        Logger.getLogger("WelcomeFragment")
    }

    private val interactionsWithDatabase by lazy {
        InteractionsWithDatabase(AppContext.applicationContext())
    }

    fun setForecast(id_city: Long) {

        deleteUselessData()

        fun getForecastFromServer(cityId: Long) = weatherAPIService.getForecastByCityId(cityId)

        getForecastFromServer(id_city).enqueue(object : Callback<ModelResponse> {

            override fun onResponse(call: Call<ModelResponse>?, response: Response<ModelResponse>?) {

                val response = response!!.body()!!

                insertResponseToDB(response)

                return

            }

            override fun onFailure(call: Call<ModelResponse>?, t: Throwable?) {}

        })

        setWeather(id_city)

        //TODO("add coroutine")//crushing when DB is empty

    }

    fun getThreeHours(id_city: Long, id_dt: Long) =
        interactionsWithDatabase.getThreeHours(id_city = id_city, id_dt = id_dt)

    fun getUpPanel(id_city: Long, id_dt: Long) = interactionsWithDatabase.getUpPanel(id_city = id_city, id_dt = id_dt)

    fun getPlace(id_city: Long) = interactionsWithDatabase.getPlace(id_city)

    private fun deleteUselessData() {

        interactionsWithDatabase.deleteUseless((System.currentTimeMillis() - THREE_HOURS_IN_MS) / 1000)

    }

    private fun insertResponseToDB(modelResponse: ModelResponse) {

        fun List<ModelWeatherForDB>.lastModelInDB(): Int =
            this.map { it.id_dt }.indexOf(interactionsWithDatabase.getLastDt(this[0].id_city))

        fun insertWeatherListToDB() {

            val list: List<ModelWeatherForDB> =
                ConverterResponseToDBType.convertResponseToWeatherTableType(modelResponse)

            if (list.isNotEmpty()) {

                val listForInsert = list.slice((list.lastModelInDB() + 1)..list.lastIndex)

                if (listForInsert.isNotEmpty()) {

                    interactionsWithDatabase.insertWeatherListData(listForInsert)

                }

            }

        }

        fun insertPlaceToDB() {

            val place = ConverterResponseToDBType.convertResponseToPlaceTableType(modelResponse)

            if (interactionsWithDatabase.isPlaceNotExist(place.id_city)) {

                interactionsWithDatabase.insertPlaceData(place)

            }

        }

        insertWeatherListToDB()
        insertPlaceToDB()

    }

    private fun setWeather(id_city: Long) {

        val id_dt = interactionsWithDatabase.getMinDt(id_city)

        welcomeView.setWeather(

            listOf(

                interactionsWithDatabase.getUpPanel(id_city, id_dt),

                ModelThreeHoursList(

                    interactionsWithDatabase.getThreeHours(id_city, id_dt)

                ), ModelDayList(

                    interactionsWithDatabase.getDays(id_city)

                )
            )
        )

    }

    override fun getFragmentManager():FragmentManager = welcomeView.getSupportFragmentManager()
}