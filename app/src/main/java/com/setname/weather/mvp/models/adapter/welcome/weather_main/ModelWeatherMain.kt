package com.setname.weather.mvp.models.adapter.welcome.weather_main

import com.setname.weather.mvp.interfaces.welcome.adapter.list.ListWelcome
import com.setname.weather.mvp.models.retrofit.ModelResponse
import kotlin.math.roundToInt

data class ModelWeatherMain(val cityName:String,
                            val descriptor:String,
                            val currentTemp:String): ListWelcome() {

    constructor(modelResponse:ModelResponse) : this(
        modelResponse.city.name,
        modelResponse.list[0].weather[0].description,
        modelResponse.list[0].main.temp.roundToInt().toString().plus("°")) {

    }

    override fun getListItemType(): Int = ListWelcome.ListWelcomeType.WEATHER_UP_PANEL.type

}