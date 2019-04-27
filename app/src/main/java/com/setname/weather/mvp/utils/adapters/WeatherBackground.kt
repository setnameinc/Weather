package com.setname.weather.mvp.utils.adapters

abstract class WeatherBackground {

    enum class Background(val type: Int) {

        CLEAR(0),
        RAIN(1);

    }

    abstract fun getListItemType(): Int

}