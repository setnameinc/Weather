package com.setname.weather.mvp.presenters.welcome

import com.setname.weather.mvp.interfaces.welcome.WelcomeView
import com.setname.weather.mvp.models.adapter.welcome.day.ModelDay
import com.setname.weather.mvp.models.adapter.welcome.hour.ModelThreeHours
import com.setname.weather.mvp.models.adapter.welcome.lists.day.ModelDayList
import com.setname.weather.mvp.models.adapter.welcome.lists.hour.ModelThreeHoursList
import com.setname.weather.mvp.models.database.ModelWeatherForDB
import com.setname.weather.mvp.models.retrofit.ModelResponse
import com.setname.weather.mvp.presenters.welcome.with_db.InteractionsWithDatabase
import com.setname.weather.mvp.retrofit.WeatherAPIService
import com.setname.weather.mvp.utils.converters.ConverterResponseToDBType
import com.setname.weather.mvp.utils.poor.AppContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Logger

class WelcomePresenter(private var welcomeView: WelcomeView) {

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

    fun setForecast(cityID: Long) {

        deleteUselessData()

        //load current data, if it offline, load minInDB

        fun getForecastFromServer(cityId: Long) = weatherAPIService.getForecastByCityId(cityId)

        getForecastFromServer(cityID).enqueue(object : Callback<ModelResponse> {

            override fun onResponse(call: Call<ModelResponse>?, response: Response<ModelResponse>?) {

                val response = response!!.body()!!




                return

            }

            override fun onFailure(call: Call<ModelResponse>?, t: Throwable?) {}

        })

        setWeather(cityID)

    }

    private fun deleteUselessData() {

        interactionsWithDatabase.deleteUseless(System.currentTimeMillis() - THREE_HOURS_IN_MS)
        //load last forecast from server

    }

    private fun saveDataToDB(modelResponse: ModelResponse) {

        interactionsWithDatabase.insertListData(ConverterResponseToDBType.convertResponseToDBType(modelResponse))

    }

    private fun getAll() = interactionsWithDatabase.getAll()

    private fun setWeather(cityID: Long) {

        welcomeView.setWeather(
            listOf(
                interactionsWithDatabase.getUpPanelByCityId(cityID)!!,
                ModelThreeHoursList(
                    listOf(
                        ModelThreeHours(1L, "http://openweathermap.org/img/w/01n.png", 2f, 18918),
                        ModelThreeHours(2L, "http://openweathermap.org/img/w/01n.png", 2f, 18918),
                        ModelThreeHours(3L, "http://openweathermap.org/img/w/01n.png", 2f, 18918),
                        ModelThreeHours(4L, "http://openweathermap.org/img/w/01n.png", 2f, 18918),
                        ModelThreeHours(5L, "http://openweathermap.org/img/w/01n.png", 2f, 18918),
                        ModelThreeHours(6L, "http://openweathermap.org/img/w/01n.png", 2f, 18918),
                        ModelThreeHours(7L, "http://openweathermap.org/img/w/01n.png", 2f, 18918),
                        ModelThreeHours(8L, "http://openweathermap.org/img/w/01n.png", 2f, 18918),
                        ModelThreeHours(9L, "http://openweathermap.org/img/w/01n.png", 2f, 18918)
                    )
                ), ModelDayList(

                    listOf(

                        ModelDay(10L, "http://openweathermap.org/img/w/01n.png", 2f, 18918),
                        ModelDay(11L, "http://openweathermap.org/img/w/01n.png", 2f, 18918),
                        ModelDay(12L, "http://openweathermap.org/img/w/01n.png", 2f, 18918),
                        ModelDay(13L, "http://openweathermap.org/img/w/01n.png", 2f, 18918),
                        ModelDay(14L, "http://openweathermap.org/img/w/01n.png", 2f, 18918),
                        ModelDay(15L, "http://openweathermap.org/img/w/01n.png", 2f, 18918),
                        ModelDay(16L, "http://openweathermap.org/img/w/01n.png", 2f, 18918),
                        ModelDay(17L, "http://openweathermap.org/img/w/01n.png", 2f, 18918)

                    )

                )
            )
        )

    }

}