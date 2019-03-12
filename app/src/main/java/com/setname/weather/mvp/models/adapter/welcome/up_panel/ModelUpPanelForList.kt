package com.setname.weather.mvp.models.adapter.welcome.up_panel

import com.setname.weather.mvp.interfaces.welcome.adapter.list.ListWelcome

data class ModelUpPanelForList(val cityName:String,
                               val descriptor:String,
                               val currentTemp:String): ListWelcome() {

    override fun getListItemType(): Int = ListWelcome.ListWelcomeType.WEATHER_UP_PANEL.type


}