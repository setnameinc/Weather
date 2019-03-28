package com.setname.weather.mvp.models.retrofit.city

import java.util.*

data class ModelCityCoord(val lon: Float, val lat: Float) {

    fun convertToOneStrLonLat(): String =
        "${String.format(Locale.ENGLISH, "%.2f", lon)},${String.format(Locale.ENGLISH, "%.2f", lat)}"

}