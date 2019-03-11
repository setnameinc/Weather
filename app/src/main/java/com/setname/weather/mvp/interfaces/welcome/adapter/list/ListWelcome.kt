package com.setname.weather.mvp.interfaces.welcome.adapter.list

abstract class ListWelcome {

     enum class ListWelcomeType(val type: Int) {

        WEATHER_LIST(1),
         WEATHER_UP_PANEL(2)

    }

    abstract fun getListItemType(): Int

}