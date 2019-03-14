package com.setname.weather.mvp.interfaces.welcome

import com.setname.weather.mvp.interfaces.LoadingView
import com.setname.weather.mvp.models.adapter.welcome.up_panel.ModelUpPanelForList
import com.setname.weather.mvp.models.adapter.welcome.weather_weeks.ModelWeatherWeekForList

interface WelcomeView: LoadingView {

    fun setUpPanelForecast(modelWeatherUpPanel: ModelUpPanelForList?)
    fun setWeatherWeeks(modelResponseWeatherWeek: ModelWeatherWeekForList?)

}