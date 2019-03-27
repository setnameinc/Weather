package com.setname.weather.mvp.models.adapter.welcome.lists.hour

import com.setname.weather.mvp.interfaces.welcome.adapter.list_main.ListWelcome
import com.setname.weather.mvp.models.adapter.welcome.hour.ModelThreeHours

data class ModelThreeHoursList(val list: List<ModelThreeHours>) : ListWelcome() {
    override fun getListItemType(): Int = ListWelcome.ListWelcomeType.WEATHER_PER_THREE_HOURS.type
}