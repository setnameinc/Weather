package com.setname.weather.mvp.models.database

import com.setname.weather.mvp.models.retrofit.weather.ModelWeatherAdditional
import com.setname.weather.mvp.models.retrofit.weather.ModelWeatherMainInf
import com.setname.weather.mvp.models.retrofit.weather.clouds.ModelClouds
import com.setname.weather.mvp.models.retrofit.weather.wind.ModelWind
import java.io.Serializable

data class ModelWeatherForDBForDay(val main: ModelWeatherMainInf,
                                   val weather: List<ModelWeatherAdditional>,
                                   val clouds: ModelClouds,
                                   val wind: ModelWind,
                                   val dt_txt:String){



}