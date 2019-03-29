package com.setname.weather.mvp.models.database.weather

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.setname.weather.mvp.models.database.weather.day.additional.ModelWeatherForDBForDayAdditionalInfo
import com.setname.weather.mvp.models.database.weather.model_up_panel.ModelUpPanelForDB
import org.json.JSONObject

@Entity(tableName = "weather")
data class ModelWeatherForDB(
    @PrimaryKey
    val id_dt:Long,
    @ColumnInfo
    val id_city:Long,
    @ColumnInfo
    val icon:String,
    @ColumnInfo
    val temp: Float,
    @TypeConverters(JSONObject::class)
    val model_up_panel: ModelUpPanelForDB
)