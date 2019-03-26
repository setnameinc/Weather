package com.setname.weather.mvp.models.retrofit.weather

import com.setname.weather.mvp.models.retrofit.weather.clouds.ModelClouds
import com.setname.weather.mvp.models.retrofit.weather.wind.ModelWind

data class ModelWeatherList (val dt:Long,
                             val main: ModelWeatherMainInf,
                             val weather: List<ModelUpPanel>,
                             val clouds: ModelClouds,
                             val wind: ModelWind,
                             val dt_txt:String)