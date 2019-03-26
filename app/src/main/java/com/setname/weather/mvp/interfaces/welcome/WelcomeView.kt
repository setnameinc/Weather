package com.setname.weather.mvp.interfaces.welcome

import android.widget.ListView
import com.setname.weather.mvp.interfaces.LoadingView
import com.setname.weather.mvp.interfaces.welcome.adapter.list_main.ListWelcome

interface WelcomeView: LoadingView {

    fun setWeather(listView: List<ListWelcome>)

}