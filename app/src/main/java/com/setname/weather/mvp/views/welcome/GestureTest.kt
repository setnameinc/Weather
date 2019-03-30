package com.setname.weather.mvp.views.welcome

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.setname.weather.R
import java.util.logging.Logger

class GestureTest: Fragment() {

    private lateinit var viewTest:View

    private val log by lazy {

        Logger.getLogger("GestureTest")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewTest = inflater.inflate(R.layout.fragment_test_gesture, container, false)
        return viewTest
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

}