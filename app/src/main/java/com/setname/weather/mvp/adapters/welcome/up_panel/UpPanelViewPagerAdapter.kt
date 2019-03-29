package com.setname.weather.mvp.adapters.welcome.up_panel

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.setname.weather.mvp.models.adapter.welcome.up_panel.view_pager.AdditionalModelUpPanel
import com.setname.weather.mvp.models.adapter.welcome.up_panel.view_pager.MainModelUpPanel
import com.setname.weather.mvp.views.welcome.adapter.up_panel.view_pager.AdditionalPageFragmentViewPagerUpPanel
import com.setname.weather.mvp.views.welcome.adapter.up_panel.view_pager.MainPageFragmentViewPagerUpPanel

class UpPanelViewPagerAdapter constructor(
    fm: FragmentManager,
    private val modelUpPanel: com.setname.weather.mvp.models.database.weather.smart_request.ModelUpPanel
) : FragmentPagerAdapter(fm) {

    private val COUNT = 2

    override fun getItem(position: Int): Fragment? =
        when (position) {
            0 -> {
                MainPageFragmentViewPagerUpPanel.newInstance(
                    MainModelUpPanel(modelUpPanel)
                )
            }
            1 -> {
                AdditionalPageFragmentViewPagerUpPanel.newInstance(AdditionalModelUpPanel(modelUpPanel))
            }
            else -> null //never happened
        }

    override fun getCount(): Int {
        return COUNT
    }
}
