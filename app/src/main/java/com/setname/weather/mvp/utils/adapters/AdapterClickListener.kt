package com.setname.weather.mvp.utils.adapters

interface AdapterClickListener {
    fun setUpPanel(id_city:Long, id_dt:Long)
    fun setThreeHoursPanel(id_city:Long, id_dt:Long)
    fun setBackground(id_background: Int)
}