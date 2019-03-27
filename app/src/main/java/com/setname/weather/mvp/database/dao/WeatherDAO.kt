package com.setname.weather.mvp.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.setname.weather.mvp.models.adapter.welcome.day.ModelDay
import com.setname.weather.mvp.models.adapter.welcome.hour.ModelThreeHours
import com.setname.weather.mvp.models.database.ModelWeatherForDB
import com.setname.weather.mvp.models.database.smart_request.ModelUpPanel

@Dao
interface WeatherDAO{

    @Insert
    fun insertData(modelDatabase: ModelWeatherForDB)

    @Query("DELETE FROM weather WHERE id_dt > :time")
    fun deleteUseless(time: Long)

    @Insert
    fun insertListData(list: List<ModelWeatherForDB>)

    @Query("SELECT id_dt, id_city, `temp`, model_up_panel FROM weather WHERE id_city = :id_city AND id_dt = :id_dt")
    fun getUpPanel(id_city: Long, id_dt:Long): ModelUpPanel

    @Query("SELECT id_dt, id_city, `temp`, icon FROM weather WHERE id_city = :id_city AND id_dt >= :id_dt LIMIT :limit")
    fun getThreeHours(id_city: Long, id_dt:Long, limit:Long):List<ModelThreeHours>

    @Query("SELECT MIN(id_dt) FROM weather WHERE id_city = :id_city")
    fun getMinDt(id_city: Long):Long

    @Query("SELECT id_dt, id_city, `temp`, icon FROM weather WHERE id_city = :id_city LIMIT :limit")
    fun getDays(id_city: Long, limit: Long):List<ModelDay>

}