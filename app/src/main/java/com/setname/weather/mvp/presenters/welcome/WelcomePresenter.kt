package com.setname.weather.mvp.presenters.welcome

import com.setname.weather.mvp.interfaces.welcome.WelcomeView
import com.setname.weather.mvp.models.database.ModelWeatherForDB
import com.setname.weather.mvp.models.database.ModelWeatherForDBForDay
import com.setname.weather.mvp.models.responces.additionals.ModelResponseUpPanel
import com.setname.weather.mvp.models.responces.main.ModelResponseWeatherFromDB
import com.setname.weather.mvp.models.responces.additionals.ModelResponseWeatherWeek
import com.setname.weather.mvp.models.retrofit.ModelResponse
import com.setname.weather.mvp.models.retrofit.weather.ModelWeatherMainInf
import com.setname.weather.mvp.models.retrofit.weather.clouds.ModelClouds
import com.setname.weather.mvp.models.retrofit.weather.wind.ModelWind
import com.setname.weather.mvp.presenters.welcome.with_db.InteractionsWithDatabase
import com.setname.weather.mvp.retrofit.WeatherAPIService
import com.setname.weather.mvp.utils.poor.AppContext
import com.setname.weather.mvp.views.welcome.WelcomeFragment
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

        log.info(interactionsWithDatabase.getAllData("18918_2131231231")!![0].toString())
        /*interactionsWithDatabase.insertData(ModelWeatherForDB("18918_2131231231",
            ModelWeatherForDBForDay(
                ModelWeatherMainInf(1.3f,1.3f,1.3f,1.3f,1.3f,1.3f,1, 1.3f),
                listOf(),
                ModelClouds(2),
                ModelWind(1.3f, 1.3f),
                "123 12 31 2 43 4534 5 34"
                )))*/

    }

    fun saveDataToDB(modelResponseWeatherFromDB: ModelResponseWeatherFromDB) {


    }

    fun setWeatherForecastForWelcomeFragment() {

        //from DB to list in view

    }


}