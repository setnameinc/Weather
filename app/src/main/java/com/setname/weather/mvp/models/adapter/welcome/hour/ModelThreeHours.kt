package com.setname.weather.mvp.models.adapter.welcome.hour

import android.arch.persistence.room.ColumnInfo

data class ModelThreeHours(

    @ColumnInfo(name = "id_dt")
    val id_dt: Long,
    @ColumnInfo(name = "id_city")
    val id_city: Long,
    @ColumnInfo(name = "temp")
    val temp: Float,
    @ColumnInfo(name = "icon")
    val image_url: String

)