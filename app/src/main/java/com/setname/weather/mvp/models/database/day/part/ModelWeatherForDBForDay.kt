package com.setname.weather.mvp.models.database.day.part

import com.setname.weather.mvp.models.retrofit.weather.ModelWeatherDescAndIcon

data class ModelWeatherForDBForDay(
    val weather: ModelWeatherDescAndIcon,
    val temp: Float,
    val temp_min: Float,
    val temp_max: Float
)