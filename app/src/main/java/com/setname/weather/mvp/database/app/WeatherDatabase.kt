package com.setname.weather.mvp.database.app

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.setname.weather.mvp.database.converters.ConverterWeatherForDBForDayAdditionalInfo
import com.setname.weather.mvp.database.converters.ConverterModelUpPanelForDB
import com.setname.weather.mvp.database.dao.WeatherDAO
import com.setname.weather.mvp.models.database.place.ModelPlace
import com.setname.weather.mvp.models.database.weather.ModelWeatherForDB

@Database(entities = arrayOf(ModelWeatherForDB::class, ModelPlace::class), version = 1, exportSchema = false)
@TypeConverters(ConverterModelUpPanelForDB::class, ConverterWeatherForDBForDayAdditionalInfo::class)

abstract class WeatherDatabase: RoomDatabase(){

    abstract fun weatherDAO():WeatherDAO

    companion object {

        @Volatile private var INSTANCE: WeatherDatabase? = null

        fun getInstance(context: Context): WeatherDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                WeatherDatabase::class.java, "Sample.db")
                .allowMainThreadQueries()//for time
                    //TODO(allowMainThreadQueries) after coroutines delete it
                .build()
    }

}