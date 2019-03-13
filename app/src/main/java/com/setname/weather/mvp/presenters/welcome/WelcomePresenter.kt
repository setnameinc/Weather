package com.setname.weather.mvp.presenters.welcome

import com.setname.weather.mvp.interfaces.welcome.WelcomeView
import com.setname.weather.mvp.models.database.ModelWeatherForDB
import com.setname.weather.mvp.models.database.day.additional.ModelWeatherForDBForDayAdditionalInfo
import com.setname.weather.mvp.models.database.day.additional.additional.ModelWeatherForDBForDayAdditionalInfoMainInf
import com.setname.weather.mvp.models.database.day.part.ModelWeatherForDBForDay
import com.setname.weather.mvp.models.responces.main.ModelResponseWeatherFromDB
import com.setname.weather.mvp.models.retrofit.ModelResponse
import com.setname.weather.mvp.models.retrofit.weather.ModelWeatherDescAndIcon
import com.setname.weather.mvp.models.retrofit.weather.clouds.ModelClouds
import com.setname.weather.mvp.models.retrofit.weather.wind.ModelWind
import com.setname.weather.mvp.presenters.welcome.with_db.InteractionsWithDatabase
import com.setname.weather.mvp.retrofit.WeatherAPIService
import com.setname.weather.mvp.utils.poor.AppContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Logger

class WelcomePresenter(private var welcomeView: WelcomeView) {

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

        fun getForecastFromServer(cityId: Long) = weatherAPIService.getForecastByCityId(cityId)

        getForecastFromServer(cityID).enqueue(object : Callback<ModelResponse> {

            override fun onResponse(call: Call<ModelResponse>?, response: Response<ModelResponse>?) {

                val response = response!!.body()!!

                //TODO(make good architecture)

//                val modelResponseWeatherFromServerOWMToDB =
//                call save "modelResponseWeatherFromServerOWMToDB" to DB
//                setWeatherForecastForWelcomeFragment(modelResponseWeatherFromDBToWelcomeFragmentListWelcome)

                return

            }

            override fun onFailure(call: Call<ModelResponse>?, t: Throwable?) {}

        })

        check()

    }

    fun check(){

        interactionsWithDatabase.insertData(ModelWeatherForDB("18918_25940323",
            ModelWeatherForDBForDay(ModelWeatherDescAndIcon(1, "", "", ""), 2f, 3f, 4f),
            ModelWeatherForDBForDayAdditionalInfo(ModelWeatherForDBForDayAdditionalInfoMainInf(1f,1f,1f,1,1f),
                ModelClouds(1), ModelWind(1f,1f)
            )))

    }

    fun saveDataToDB(modelResponseWeatherFromDB: ModelResponseWeatherFromDB) {


    }

    fun setWeatherForecastForWelcomeFragment() {

        //from DB to list in view

    }


}