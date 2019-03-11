package com.setname.weather.mvp.models.retrofit.weather

data class ModelWeatherMainInf (val temp:Float, val  temp_min:Float, val temp_max:Float,
                                val pressure:Float, val sea_level:Float, val grnd_level:Float,
                                val humidity:Byte, val temp_kf:Float)