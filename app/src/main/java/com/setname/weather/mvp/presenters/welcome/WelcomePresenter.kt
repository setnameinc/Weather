package com.setname.weather.mvp.presenters.welcome

import com.setname.weather.mvp.interfaces.welcome.WelcomeView
import com.setname.weather.mvp.models.adapter.welcome.weather_main.ModelWeatherMain
import com.setname.weather.mvp.models.retrofit.ModelResponse
import com.setname.weather.mvp.retrofit.WeatherAPIService
import com.setname.weather.mvp.views.welcome.WelcomeFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.logging.Logger
import kotlin.math.roundToInt

class WelcomePresenter(private var welcomeView: WelcomeView) {

    private val weatherAPIService by lazy {
        WeatherAPIService.retrofit
    }

    private val log by lazy {
        Logger.getLogger(::WelcomeFragment.javaClass.name)
    }

    fun setForecast(cityID:Long){

        fun getForecastFromServer(cityId:Long) = weatherAPIService.getForecastByCityId(cityId)

        getForecastFromServer(cityID).enqueue(object : Callback<ModelResponse> {

            override fun onResponse(call: Call<ModelResponse>?, response: Response<ModelResponse>?) {

                val response = response!!.body()!!

                welcomeView.showRecyclerViewForecast(response, ModelWeatherMain(response))

                return

                TODO("Dirty")//response.list[0].main.temp.roundToInt().toString().plus("°")

            }

            override fun onFailure(call: Call<ModelResponse>?, t: Throwable?) {

                return

            }

        })

    }



}