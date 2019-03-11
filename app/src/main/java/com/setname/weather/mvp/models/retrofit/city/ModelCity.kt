package com.setname.weather.mvp.models.retrofit.city

data class ModelCity (val id:Long, val name:String, var coord: ModelCityCoord,
                      val country:String)