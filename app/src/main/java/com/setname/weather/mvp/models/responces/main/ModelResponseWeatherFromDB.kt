package com.setname.weather.mvp.models.responces.main

import com.setname.weather.mvp.models.responces.additionals.ModelResponseUpPanel
import com.setname.weather.mvp.models.responces.additionals.ModelResponseWeatherWeek

data class ModelResponseWeatherFromDB(val modelWeatherUpPanel: ModelResponseUpPanel?,
                                      val modelResponseWeatherWeek: ModelResponseWeatherWeek?)