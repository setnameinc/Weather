package com.setname.weather.mvp.models.adapter.welcome.weather_weeks

import com.setname.weather.mvp.interfaces.welcome.adapter.list.ListWelcome
import com.setname.weather.mvp.models.adapter.welcome.weather_weeks.day.ModelDayForModelWeatherWeek

data class ModelWeatherWeekForList(val listOfModelDays: List<ModelDayForModelWeatherWeek>) : ListWelcome() {

    override fun getListItemType(): Int = ListWelcome.ListWelcomeType.WEATHER_WEEK.type

}
