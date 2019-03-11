package com.setname.weather.mvp.interfaces.welcome

import com.setname.weather.mvp.interfaces.LoadingView
import com.setname.weather.mvp.models.adapter.welcome.weather_main.ModelWeatherMain
import com.setname.weather.mvp.models.retrofit.ModelResponse

interface WelcomeView: LoadingView {

    fun showRecyclerViewForecast(modelResponse: ModelResponse?, modelWeatherMain: ModelWeatherMain?)
    fun showCurrentDayForecast()

}