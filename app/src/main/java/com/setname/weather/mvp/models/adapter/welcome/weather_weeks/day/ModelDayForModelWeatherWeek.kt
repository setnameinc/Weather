package com.setname.weather.mvp.models.adapter.welcome.weather_weeks.day

import com.setname.weather.mvp.models.adapter.welcome.weather_weeks.day.hour.ModelHourForModelDay

data class ModelDayForModelWeatherWeek(val dt:Long,
                                       val temp_min:Float,
                                       val temp_max:Float,
                                       val listOfModelHours: List<ModelHourForModelDay>)