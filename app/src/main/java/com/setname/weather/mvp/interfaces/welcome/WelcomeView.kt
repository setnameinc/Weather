package com.setname.weather.mvp.interfaces.welcome

import android.support.v4.app.FragmentManager
import com.setname.weather.mvp.interfaces.LoadingView
import com.setname.weather.mvp.interfaces.welcome.adapters.list_main.ListWelcome

interface WelcomeView{

    fun setWeather(listView: List<ListWelcome>)

    fun getSupportFragmentManager():FragmentManager

}