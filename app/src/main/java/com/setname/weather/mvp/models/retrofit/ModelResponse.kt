package com.setname.weather.mvp.models.retrofit

import com.setname.weather.mvp.models.retrofit.city.ModelCity
import com.setname.weather.mvp.models.retrofit.weather.ModelWeatherList

data class ModelResponse(val cod:Int,
                         val message:String,
                         val city: ModelCity,
                         val count:Int,
                         val list:List<ModelWeatherList>)