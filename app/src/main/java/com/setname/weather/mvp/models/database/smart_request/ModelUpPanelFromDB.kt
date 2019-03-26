package com.setname.weather.mvp.models.database.smart_request

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.TypeConverters
import com.setname.weather.mvp.interfaces.welcome.adapter.list_main.ListWelcome
import com.setname.weather.mvp.models.database.model_up_panel.ModelUpPanelForDB
import org.json.JSONObject

data class ModelUpPanelFromDB(

    @ColumnInfo(name = "id_city")
    val id_city:Long,
    @ColumnInfo(name = "id_dt")
    val id_dt: Long,
    @ColumnInfo(name = "temp")
    val temp: Float,
    @TypeConverters(JSONObject::class)
    val model_up_panel: ModelUpPanelForDB

): ListWelcome(){
    override fun getListItemType(): Int = ListWelcome.ListWelcomeType.WEATHER_UP_PANEL.type

}