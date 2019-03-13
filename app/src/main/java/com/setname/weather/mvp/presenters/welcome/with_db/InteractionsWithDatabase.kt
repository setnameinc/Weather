package com.setname.weather.mvp.presenters.welcome.with_db

import android.content.Context
import com.setname.weather.mvp.database.app.WeatherDatabase
import com.setname.weather.mvp.database.dao.WeatherDAO
import com.setname.weather.mvp.models.database.ModelWeatherForDB

class InteractionsWithDatabase(context: Context){

    private var weatherDAO: WeatherDAO? = WeatherDatabase.getInstance(context).weatherDAO()

    fun getAllData(id:String):List<ModelWeatherForDB>?{

        return weatherDAO?.getAll(id)

    }

    fun insertData(modelWeatherForDB: ModelWeatherForDB){

        weatherDAO?.insert(modelWeatherForDB)

    }

    //add voids for :
    // 1) getting main or additional inf
    // 2) remove oldest time

}