package com.setname.weather.mvp.views.welcome.tests

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.setname.weather.R
import com.setname.weather.mvp.utils.gestures.pinch.PinchToCloseCurrentCity
import kotlinx.android.synthetic.main.fragment_test_gesture.view.*
import java.util.logging.Logger


class GestureTest : Fragment() {

    private lateinit var pinchToCloseCurrentCity: PinchToCloseCurrentCity

    private lateinit var viewGestureTest: View

    private lateinit var viewTest:View

    private val log by lazy {

        Logger.getLogger("GestureTest")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewGestureTest = inflater.inflate(R.layout.fragment_test_gesture, container, false)
        pinchToCloseCurrentCity = PinchToCloseCurrentCity(ViewConfiguration.get(context))

        return viewGestureTest
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewTest = viewGestureTest.fragment_test_gesture_view

        viewGestureTest.setOnTouchListener {

            v, event -> pinchToCloseCurrentCity.onTouch(viewTest, event)

        }

    }

}