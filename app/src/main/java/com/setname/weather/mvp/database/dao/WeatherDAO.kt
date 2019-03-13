package com.setname.weather.mvp.database.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.setname.weather.mvp.models.database.ModelWeatherForDB

@Dao
interface WeatherDAO{

    @Query("SELECT * FROM weather WHERE id = :id")
    fun getAll(id:String): List<ModelWeatherForDB>

    @Insert
    fun insert(modelDatabase: ModelWeatherForDB)

}