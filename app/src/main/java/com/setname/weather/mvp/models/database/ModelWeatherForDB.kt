package com.setname.weather.mvp.models.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import org.json.JSONObject

@Entity(tableName = "weather")
data class ModelWeatherForDB(
    @PrimaryKey
    val id: String,
    @TypeConverters(JSONObject::class)
    val modelWeatherForDBForDay: ModelWeatherForDBForDay
)