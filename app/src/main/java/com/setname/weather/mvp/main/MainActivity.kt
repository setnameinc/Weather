package com.setname.weather.mvp.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import com.setname.weather.R
import com.setname.weather.mvp.views.welcome.WelcomeFragment
import com.setname.weather.mvp.views.welcome.tests.GestureTest
import com.setname.weather.mvp.views.welcome.tests.RainAnimationFragment
import com.setname.weather.mvp.views.welcome.tests.SelectorTest
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFullScreen()

        supportFragmentManager.beginTransaction().replace(
            R.id.main_container,
            WelcomeFragment()
        ).commit()

        /*supportFragmentManager.beginTransaction().replace(
            R.id.main_container,
            GestureTest()
        ).commit()*/

        /*supportFragmentManager.beginTransaction().replace(
            R.id.main_container,
            SelectorTest()
        ).commit()*/

        /*supportFragmentManager.beginTransaction().replace(
            R.id.background_container,
            RainAnimationFragment()
        ).commit()*/

    }

    fun setFullScreen() {

        window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        window.decorView.setOnSystemUiVisibilityChangeListener { visibility ->

            if (visibility != View.SYSTEM_UI_FLAG_FULLSCREEN){

                CoroutineScope(Dispatchers.Main).launch {

                    withContext(Dispatchers.Main){

                        delay(3000)
                        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

                    }

                }

            }

        }

    }

    override fun onStart() {

        super.onStart()
    }
}

