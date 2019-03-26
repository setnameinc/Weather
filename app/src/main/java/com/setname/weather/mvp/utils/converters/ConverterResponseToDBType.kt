package com.setname.weather.mvp.utils.converters

import com.setname.weather.mvp.models.database.ModelWeatherForDB
import com.setname.weather.mvp.models.database.day.additional.ModelWeatherForDBForDayAdditionalInfo
import com.setname.weather.mvp.models.database.day.additional.additional.ModelWeatherForDBForDayAdditionalInfoMainInf
import com.setname.weather.mvp.models.database.model_up_panel.ModelUpPanelForDB
import com.setname.weather.mvp.models.retrofit.ModelResponse

object ConverterResponseToDBType {

    fun convertResponseToDBType(modelResponse: ModelResponse): List<ModelWeatherForDB> =
        modelResponse.list.map { modelWeatherList ->
            ModelWeatherForDB(
                id_dt = modelWeatherList.dt,
                id_city = modelResponse.city.id,
                icon = modelWeatherList.weather[0].icon,
                temp = modelWeatherList.main.temp,
                model_up_panel = ModelUpPanelForDB(
                    modelWeatherList.weather[0].main,
                    modelWeatherList.weather[0].description
                ),
                additional = ModelWeatherForDBForDayAdditionalInfo(
                    ModelWeatherForDBForDayAdditionalInfoMainInf(modelWeatherList.main),
                    modelWeatherList.clouds,
                    modelWeatherList.wind
                )

            )
        }.toList()


}