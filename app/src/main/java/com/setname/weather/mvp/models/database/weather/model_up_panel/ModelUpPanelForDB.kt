package com.setname.weather.mvp.models.database.weather.model_up_panel

import com.setname.weather.mvp.models.database.weather.day.additional.ModelWeatherForDBForDayAdditionalInfo

data class ModelUpPanelForDB(val main:String,
                             val description:String,
                             val additional: ModelWeatherForDBForDayAdditionalInfo
)