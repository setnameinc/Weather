package com.setname.weather.mvp.database.converters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.setname.weather.mvp.models.database.ModelWeatherForDBForDay

class ConverterWeatherModelForDB{

    companion object {

        @TypeConverter
        @JvmStatic
        fun fromString(value: String): ModelWeatherForDBForDay {

            val list = object : TypeToken <ModelWeatherForDBForDay>(){}.type

            return Gson().fromJson(value, list)

        }

        @TypeConverter
        @JvmStatic
        fun fromArrayList(list: ModelWeatherForDBForDay): String {
            val gson = Gson()
            return gson.toJson(list)
        }


    }

}