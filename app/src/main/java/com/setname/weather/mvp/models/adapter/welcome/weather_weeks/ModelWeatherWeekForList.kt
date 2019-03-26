package com.setname.weather.mvp.models.adapter.welcome.weather_weeks

import com.setname.weather.mvp.interfaces.welcome.adapter.list_main.ListWelcome
import com.setname.weather.mvp.models.adapter.welcome.weather_weeks.day.ModelDayForModelWeatherWeek
import com.setname.weather.mvp.models.database.ModelWeatherForDB

data class ModelWeatherWeekForList(val list: List<ModelWeatherForDB>) : ListWelcome() {

    override fun getListItemType(): Int = ListWelcome.ListWelcomeType.WEATHER_WEEK.type

}
