package com.setname.weather.mvp.presenters.welcome

import com.setname.weather.mvp.interfaces.welcome.WelcomeView
import com.setname.weather.mvp.models.adapter.welcome.day.ModelDay
import com.setname.weather.mvp.models.adapter.welcome.lists.day.ModelDayList
import com.setname.weather.mvp.models.adapter.welcome.lists.hour.ModelThreeHoursList
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

        fun getForecastFromServer(cityId: Long) = weatherAPIService.getForecastByCityId(cityId)

        getForecastFromServer(cityID).enqueue(object : Callback<ModelResponse> {

            override fun onResponse(call: Call<ModelResponse>?, response: Response<ModelResponse>?) {

                val response = response!!.body()!!

                //insert to DB

                return

            }

            override fun onFailure(call: Call<ModelResponse>?, t: Throwable?) {}

        })

        setWeather(cityID)

    }

    fun getThreeHours(id_city: Long, id_dt: Long) =
        interactionsWithDatabase.getThreeHours(id_city = id_city, id_dt = id_dt)

    fun getUpPanel(id_city: Long, id_dt: Long) = interactionsWithDatabase.getUpPanel(id_city = id_city, id_dt = id_dt)

    private fun deleteUselessData() {

        interactionsWithDatabase.deleteUseless(System.currentTimeMillis() - THREE_HOURS_IN_MS)
        //load last forecast from server

    }

    private fun saveDataToDB(modelResponse: ModelResponse) {

        interactionsWithDatabase.insertListData(ConverterResponseToDBType.convertResponseToDBType(modelResponse))

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

}