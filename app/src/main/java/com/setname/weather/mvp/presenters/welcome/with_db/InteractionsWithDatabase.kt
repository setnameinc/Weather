package com.setname.weather.mvp.presenters.welcome.with_db

import android.content.Context
import com.setname.weather.mvp.database.app.WeatherDatabase
import com.setname.weather.mvp.database.dao.WeatherDAO
import com.setname.weather.mvp.models.database.ModelWeatherForDB

class InteractionsWithDatabase(context: Context) {

    private var weatherDAO: WeatherDAO? = WeatherDatabase.getInstance(context).weatherDAO()

    fun getAll(): List<ModelWeatherForDB>? = weatherDAO?.getAll()

    fun getByCityCode(city_id: Long): List<ModelWeatherForDB>? = weatherDAO?.getByCityCode(city_id)

    fun insertData(modelWeatherForDB: ModelWeatherForDB) {

        weatherDAO?.insertData(modelWeatherForDB)

    }

    fun insertListData(list: List<ModelWeatherForDB>) {

        weatherDAO?.insertListData(list)

    }

    fun deleteUseless(time: Long) {

        weatherDAO?.deleteUseless(time)

    }

    fun getUpPanelByCityId(city_id: Long) = weatherDAO?.getUpPanelByCityId(city_id)

}