package com.setname.weather.mvp.views.welcome.adapter.up_panel.view_pager

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setname.weather.R
import com.setname.weather.mvp.models.adapter.welcome.up_panel.view_pager.AdditionalModelUpPanel
import java.util.logging.Logger

class AdditionalPageFragmentViewPagerUpPanel : Fragment() {

    val log by lazy {

        Logger.getLogger("AddPageFragment")

    }

    private lateinit var viewLocal: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewLocal = inflater.inflate(R.layout.adapter_weather_up_panel_view_pager_add_page, container, false)

        /*val additionalModelUpPanel: AdditionalModelUpPanel =
            arguments!!.getParcelable<Parcelable>("modelUpAdd") as AdditionalModelUpPanel

        log.info("temp is ${additionalModelUpPanel.clouds.all}")*/

        Logger.getLogger("ViewPager").info("1")

        return viewLocal
    }

    companion object {
        fun newInstance(model: AdditionalModelUpPanel): Fragment {
            val fragment = MainPageFragmentViewPagerUpPanel()
            val args = Bundle()
            args.putParcelable("modelUpAdd", model as Parcelable)
            fragment.arguments = args
            return fragment
        }
    }

}
