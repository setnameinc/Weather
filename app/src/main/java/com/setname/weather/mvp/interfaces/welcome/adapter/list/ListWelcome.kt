package com.setname.weather.mvp.interfaces.welcome.adapter.list

abstract class ListWelcome {

     enum class ListWelcomeType(val type: Int) {

         WEATHER_UP_PANEL(1),
         WEATHER_WEEK(2),


    }

    abstract fun getListItemType(): Int

}