package com.setname.weather.mvp.database.converters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.setname.weather.mvp.models.database.weather.model_up_panel.ModelUpPanelForDB

class ConverterModelUpPanelForDB{

    companion object {

        @TypeConverter
        @JvmStatic
        fun fromString(value: String): ModelUpPanelForDB {

            val list = object : TypeToken <ModelUpPanelForDB>(){}.type

            return Gson().fromJson(value, list)

        }

        @TypeConverter
        @JvmStatic
        fun fromArrayList(list: ModelUpPanelForDB): String {
            val gson = Gson()
            return gson.toJson(list)
        }


    }

}