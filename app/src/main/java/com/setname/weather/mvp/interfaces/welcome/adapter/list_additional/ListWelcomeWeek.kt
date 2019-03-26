package com.setname.weather.mvp.interfaces.welcome.adapter.list_additional

abstract class ListWelcomeWeek {

    enum class ListWelcomeWeekType(val type: Int) {

        PER_THREE_HOURS(1),
        PER_DAY(2);

    }

    abstract fun getListItemType(): Int

}