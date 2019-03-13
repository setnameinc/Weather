package com.setname.weather.mvp.database.converters

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.setname.weather.mvp.models.database.day.additional.ModelWeatherForDBForDayAdditionalInfo

class ConverterWeatherForDBForDayAdditionalInfo {

    companion object {

        @TypeConverter
        @JvmStatic
        fun fromString(value: String): ModelWeatherForDBForDayAdditionalInfo {

            val list = object : TypeToken<ModelWeatherForDBForDayAdditionalInfo>() {}.type

            return Gson().fromJson(value, list)

        }

        @TypeConverter
        @JvmStatic
        fun fromArrayList(list: ModelWeatherForDBForDayAdditionalInfo): String {
            val gson = Gson()
            return gson.toJson(list)
        }


    }

}
