package com.setname.weather.mvp.models.database.place

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "place")
data class ModelPlace(

    @PrimaryKey
    val id_city:Long,
    @ColumnInfo
    val coord:String,
    @ColumnInfo
    val name_city:String

)