package com.setname.weather.mvp.models.database.weather.day.additional

import com.setname.weather.mvp.models.database.weather.day.additional.additional.ModelWeatherForDBForDayAdditionalInfoMainInf
import com.setname.weather.mvp.models.retrofit.weather.clouds.ModelClouds
import com.setname.weather.mvp.models.retrofit.weather.wind.ModelWind


data class ModelWeatherForDBForDayAdditionalInfo(
    val info: ModelWeatherForDBForDayAdditionalInfoMainInf,
    val clouds: ModelClouds,
    val wind: ModelWind
)