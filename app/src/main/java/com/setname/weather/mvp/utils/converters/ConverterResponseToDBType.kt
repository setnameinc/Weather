package com.setname.weather.mvp.utils.converters

import com.setname.weather.mvp.models.database.place.ModelPlace
import com.setname.weather.mvp.models.database.weather.ModelWeatherForDB
import com.setname.weather.mvp.models.database.weather.day.additional.ModelWeatherForDBForDayAdditionalInfo
import com.setname.weather.mvp.models.database.weather.day.additional.additional.ModelWeatherForDBForDayAdditionalInfoMainInf
import com.setname.weather.mvp.models.database.weather.model_up_panel.ModelUpPanelForDB
import com.setname.weather.mvp.models.retrofit.ModelResponse

object ConverterResponseToDBType {

    fun convertResponseToWeatherTableType(modelResponse: ModelResponse): List<ModelWeatherForDB> =
        modelResponse.list.map { modelWeatherList ->
            ModelWeatherForDB(
                id_dt = modelWeatherList.dt,
                id_city = modelResponse.city.id,
                icon = modelWeatherList.weather[0].icon,
                temp = modelWeatherList.main.temp,
                model_up_panel = ModelUpPanelForDB(
                    modelWeatherList.weather[0].main,
                    modelWeatherList.weather[0].description,
                    ModelWeatherForDBForDayAdditionalInfo(
                        ModelWeatherForDBForDayAdditionalInfoMainInf(
                            modelWeatherList.main
                        ),
                        modelWeatherList.clouds,
                        modelWeatherList.wind
                    )
                )

            )
        }.toList()

    fun convertResponseToPlaceTableType(modelResponse: ModelResponse):ModelPlace = ModelPlace(
        id_city = modelResponse.city.id,
        name_city = modelResponse.city.name,
        coord = modelResponse.city.coord.convertToOneStrLonLat()
    )

}