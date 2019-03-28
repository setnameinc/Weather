package com.setname.weather.mvp.presenters.welcome.with_db

import android.content.Context
import com.setname.weather.mvp.database.app.WeatherDatabase
import com.setname.weather.mvp.database.dao.WeatherDAO
import com.setname.weather.mvp.models.database.ModelWeatherForDB

class InteractionsWithDatabase(context: Context) {

    private var weatherDAO: WeatherDAO = WeatherDatabase.getInstance(context).weatherDAO()

    fun insertData(modelWeatherForDB: ModelWeatherForDB) {

        weatherDAO.insertData(modelWeatherForDB)

    }

    fun insertListData(list: List<ModelWeatherForDB>) {

        weatherDAO.insertListData(list)

    }

    fun deleteUseless(time: Long) {

        weatherDAO.deleteUseless(time)

    }

    fun getUpPanel(id_city:Long, id_dt:Long) = weatherDAO.getUpPanel(id_city = id_city, id_dt = id_dt)

    fun getThreeHours(id_city: Long, id_dt: Long) = weatherDAO.getThreeHours(id_city = id_city, id_dt = id_dt, limit = 8)

    fun getMinDt(id_city: Long) = weatherDAO.getMinDt(id_city)

    fun getLastDt(id_city: Long) = weatherDAO.getLastDt(id_city)

    fun getDays(id_city: Long) = weatherDAO.getDays(id_city, 15, 8)//97200 is 1 day

    fun testDeleteAll(){
        weatherDAO.testDeleteAll()
    }

}