package com.setname.weather.mvp.models.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.setname.weather.mvp.models.database.day.main.ModelWeatherForDBForDay
import com.setname.weather.mvp.models.database.day.additional.ModelWeatherForDBForDayAdditionalInfo
import org.json.JSONObject

@Entity(tableName = "weather")
data class ModelWeatherForDB(
    @PrimaryKey
    val id_time:Long,
    @ColumnInfo
    val id_city:Long,
    @TypeConverters(JSONObject::class)
    val main: ModelWeatherForDBForDay,
    @TypeConverters(JSONObject::class)
    val additional: ModelWeatherForDBForDayAdditionalInfo
)