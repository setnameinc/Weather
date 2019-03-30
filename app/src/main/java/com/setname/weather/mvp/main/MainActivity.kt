package com.setname.weather.mvp.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import com.setname.weather.R
import com.setname.weather.mvp.views.welcome.GestureTest
import com.setname.weather.mvp.views.welcome.WelcomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        supportFragmentManager.beginTransaction().replace(
            R.id.main_container,
            WelcomeFragment()
        ).commit()

        /*supportFragmentManager.beginTransaction().replace(
            R.id.main_container,
            GestureTest()
        ).commit()*/

    }

}

