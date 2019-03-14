package com.setname.weather.mvp.models.database.day.additional.additional

import com.setname.weather.mvp.models.retrofit.weather.ModelWeatherMainInf

data class ModelWeatherForDBForDayAdditionalInfoMainInf(
    val pressure: Float,
    val sea_level: Float,
    val grnd_level: Float,
    val humidity: Byte,
    val temp_kf: Float
) {

    constructor(modelWeatherMainInf: ModelWeatherMainInf) : this(
        modelWeatherMainInf.pressure,
        modelWeatherMainInf.sea_level, modelWeatherMainInf.grnd_level, modelWeatherMainInf.humidity,
        modelWeatherMainInf.temp_kf
    )

}