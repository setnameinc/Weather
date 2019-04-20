package com.setname.weather.mvp.adapters.welcome.uppanel

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.setname.weather.mvp.models.adapter.welcome.up_panel.view_pager.AdditionalModelUpPanel
import com.setname.weather.mvp.models.adapter.welcome.up_panel.view_pager.MainModelUpPanel
import com.setname.weather.mvp.models.database.weather.smart_request.ModelUpPanel
import com.setname.weather.mvp.views.welcome.adapter.uppanel.view_pager.AdditionalPageFragmentViewPagerUpPanel
import com.setname.weather.mvp.views.welcome.adapter.uppanel.view_pager.MainPageFragmentViewPagerUpPanel
import java.util.logging.Logger

class UpPanelViewPagerAdapter constructor(
    fm: FragmentManager,
    private var modelUpPanel: ModelUpPanel
) : FragmentPagerAdapter(fm) {

    private val log by lazy {

        Logger.getLogger("ViewPager")

    }

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

    override fun getItemPosition(`object`: Any): Int {

        if (`object` is MainPageFragmentViewPagerUpPanel) {

            `object`.update(MainModelUpPanel(modelUpPanel))

        } else if (`object` is AdditionalPageFragmentViewPagerUpPanel) {

            `object`.update(AdditionalModelUpPanel(modelUpPanel))

        }

        return super.getItemPosition(`object`)
    }

    fun update(modelUpPanel: ModelUpPanel) {
        this.modelUpPanel = modelUpPanel
        notifyDataSetChanged()
    }

}
