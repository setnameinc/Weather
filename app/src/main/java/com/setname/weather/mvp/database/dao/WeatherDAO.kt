package com.setname.weather.mvp.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.setname.weather.mvp.models.database.ModelWeatherForDB

@Dao
interface WeatherDAO{

    @Query("SELECT * FROM weather")
    fun getAll():List<ModelWeatherForDB>

    @Query("SELECT * FROM weather WHERE id_city = :id_city")
    fun getByCityCode(id_city:Long): List<ModelWeatherForDB>

    @Insert
    fun insertData(modelDatabase: ModelWeatherForDB)

    @Query("DELETE FROM weather WHERE id_time > :time")
    fun deleteUseless(time: Long)

    @Query("SELECT * FROM weather WHERE id_city = :city_code LIMIT 8")
    fun getByCityCodeWithLimit8(city_code: Long): List<ModelWeatherForDB>

    @Insert
    fun insertListData(list: List<ModelWeatherForDB>)//

    @Query("SELECT MIN(id_time) FROM weather WHERE id_city = :city_code")
    fun getCurrentWeather(city_code: Long): Long

}