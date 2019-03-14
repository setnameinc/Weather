package com.setname.weather.mvp.utils.converters

import com.setname.weather.mvp.models.database.ModelWeatherForDB
import com.setname.weather.mvp.models.database.day.additional.ModelWeatherForDBForDayAdditionalInfo
import com.setname.weather.mvp.models.database.day.additional.additional.ModelWeatherForDBForDayAdditionalInfoMainInf
import com.setname.weather.mvp.models.database.day.main.ModelWeatherForDBForDay
import com.setname.weather.mvp.models.retrofit.ModelResponse

object ConverterResponseToDBType {

    fun convertResponseToDBType(modelResponse: ModelResponse): List<ModelWeatherForDB> =
        modelResponse.list.map { modelWeatherList ->
            ModelWeatherForDB(
                modelWeatherList.dt,
                modelResponse.city.id,
                ModelWeatherForDBForDay(
                    modelWeatherList.weather[0],
                    modelWeatherList.main.temp,
                    modelWeatherList.main.temp_min,
                    modelWeatherList.main.temp_max
                ),
                ModelWeatherForDBForDayAdditionalInfo(
                    ModelWeatherForDBForDayAdditionalInfoMainInf(modelWeatherList.main),
                    modelWeatherList.clouds,
                    modelWeatherList.wind
                )
            )
        }.toList()


}