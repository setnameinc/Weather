package com.setname.weather.mvp.views.welcome.adapter.uppanel.view_pager

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setname.weather.R
import com.setname.weather.mvp.interfaces.welcome.adapters.up_panel.view_pager.UpdateViewPager
import com.setname.weather.mvp.models.adapter.welcome.up_panel.view_pager.AdditionalModelUpPanel
import kotlinx.android.synthetic.main.adapter_weather_up_panel_view_pager_additional_page.view.*
import java.util.logging.Logger

class AdditionalPageFragmentViewPagerUpPanel : Fragment(), UpdateViewPager {

    override fun update(model: Any) {
        setView(model as AdditionalModelUpPanel)
    }

    private lateinit var viewLocal: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewLocal = inflater.inflate(R.layout.adapter_weather_up_panel_view_pager_additional_page, container, false)

        val additionalModelUpPanel: AdditionalModelUpPanel =
            arguments!!.getParcelable<Parcelable>("modelUpAdd") as AdditionalModelUpPanel

        log.info("${additionalModelUpPanel}")

        setView(additionalModelUpPanel)

        return viewLocal
    }

    @SuppressLint("SetTextI18n")
    private fun setView(additionalModelUpPanel:AdditionalModelUpPanel){

        viewLocal.apply {

            adapter_weather_up_panel_view_pager_additional_page_clouds_mutable.text =
                "${additionalModelUpPanel.clouds.all}%"
            adapter_weather_up_panel_view_pager_additional_page_wind_mutable.text =
                "${Math.round(additionalModelUpPanel.wind.speed)} m/s"
            adapter_weather_up_panel_view_pager_additional_page_humidity_mutable.text =
                additionalModelUpPanel.info.humidity.toString()
            adapter_weather_up_panel_view_pager_additional_page_presure_mutable.text =
                additionalModelUpPanel.info.pressure.toString()
            adapter_weather_up_panel_view_pager_additional_page_sea_level_mutable.text =
                additionalModelUpPanel.info.sea_level.toString()
            adapter_weather_up_panel_view_pager_additional_page_grnd_level_mutable.text =
                additionalModelUpPanel.info.grnd_level.toString()

        }

    }

    companion object {
        fun newInstance(model: AdditionalModelUpPanel): Fragment {
            val fragment = AdditionalPageFragmentViewPagerUpPanel()
            val args = Bundle()
            args.putParcelable("modelUpAdd", model as Parcelable)
            fragment.arguments = args
            return fragment
        }
    }

    val log by lazy {

        Logger.getLogger("AddPageFragment")

    }

}
