package com.setname.weather.mvp.models.responces.additionals

import com.setname.weather.mvp.interfaces.welcome.adapter.list.ListWelcome
import com.setname.weather.mvp.models.retrofit.ModelResponse
import kotlin.math.roundToInt

class ModelResponseUpPanel(val cityName:String,
                           val descriptor:String,
                           val currentTemp:String) {

    constructor(modelResponse: ModelResponse) : this(
        modelResponse.city.name,
        modelResponse.list[0].weather[0].description,
        modelResponse.list[0].main.temp.roundToInt().toString().plus("°")) {

    }
}