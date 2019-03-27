package com.setname.weather.mvp.models.adapter.welcome.day

import android.arch.persistence.room.ColumnInfo

data class ModelDay(
    @ColumnInfo(name = "id_city")
    val id_city: Long,
    @ColumnInfo(name = "id_dt")
    val id_dt: Long,
    @ColumnInfo(name = "temp")
    val temp: Float,
    @ColumnInfo(name = "icon")
    val image_url: String
)