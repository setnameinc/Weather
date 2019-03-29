package com.setname.weather.mvp.interfaces.welcome.adapters.list_main

abstract class ListWelcome {

     enum class ListWelcomeType(val type: Int) {

         WEATHER_UP_PANEL(0),
         WEATHER_PER_THREE_HOURS(1),
         WEATHER_PER_DAY(2);

    }

    abstract fun getListItemType(): Int

}