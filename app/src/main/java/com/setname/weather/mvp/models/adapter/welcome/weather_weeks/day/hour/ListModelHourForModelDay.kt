package com.setname.weather.mvp.models.adapter.welcome.weather_weeks.day.hour

import com.setname.weather.mvp.interfaces.welcome.adapter.list_additional.ListWelcomeWeek

data class ListModelHourForModelDay(val listOfModelHours: List<ModelHourForModelDay>): ListWelcomeWeek() {
    override fun getListItemType(): Int = ListWelcomeWeek.ListWelcomeWeekType.PER_THREE_HOURS.type
}