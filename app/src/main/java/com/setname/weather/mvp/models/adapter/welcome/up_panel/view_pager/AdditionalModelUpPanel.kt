package com.setname.weather.mvp.models.adapter.welcome.up_panel.view_pager

import android.os.Parcelable
import com.setname.weather.mvp.models.database.weather.day.additional.additional.ModelWeatherForDBForDayAdditionalInfoMainInf
import com.setname.weather.mvp.models.database.weather.smart_request.ModelUpPanel
import com.setname.weather.mvp.models.retrofit.weather.clouds.ModelClouds
import com.setname.weather.mvp.models.retrofit.weather.wind.ModelWind
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AdditionalModelUpPanel(
    val info: AdditionalInfo,
    val clouds: Clouds,
    val wind: Wind
) : Parcelable {

    constructor(modelUpPanel: ModelUpPanel) : this(
        AdditionalInfo(modelUpPanel.model_up_panel.additional.info),
        Clouds(modelUpPanel.model_up_panel.additional.clouds),
        Wind(modelUpPanel.model_up_panel.additional.wind)
    )

}

@Parcelize
data class Wind(val speed: Float, val deg: Float) : Parcelable {

    constructor(modelWind: ModelWind) : this(

        modelWind.speed,
        modelWind.deg

    )

}

@Parcelize
data class Clouds(val all: Int) : Parcelable {

    constructor(modelClouds: ModelClouds) : this(

        modelClouds.all

    )

}

@Parcelize
data class AdditionalInfo(
    val pressure: Float,
    val sea_level: Float,
    val grnd_level: Float,
    val humidity: Byte,
    val temp_kf: Float
) : Parcelable {

    constructor(modelWeather: ModelWeatherForDBForDayAdditionalInfoMainInf) : this(
        modelWeather.pressure,
        modelWeather.sea_level,
        modelWeather.grnd_level,
        modelWeather.humidity,
        modelWeather.temp_kf
    )

}