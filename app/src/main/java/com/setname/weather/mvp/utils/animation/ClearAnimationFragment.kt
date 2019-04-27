package com.setname.weather.mvp.utils.animation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setname.weather.R

class ClearAnimationFragment : Fragment() {

    private lateinit var viewClearAnimationFragment: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewClearAnimationFragment = inflater.inflate(R.layout.background_clear, container, false)

        return viewClearAnimationFragment

    }

}