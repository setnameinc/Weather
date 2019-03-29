package com.setname.weather.mvp.models.adapter.welcome.lists.day

import com.setname.weather.mvp.interfaces.welcome.adapters.list_main.ListWelcome
import com.setname.weather.mvp.models.adapter.welcome.day.ModelDay

data class ModelDayList(val list:List<ModelDay>): ListWelcome() {
    override fun getListItemType(): Int = ListWelcome.ListWelcomeType.WEATHER_PER_DAY.type
}