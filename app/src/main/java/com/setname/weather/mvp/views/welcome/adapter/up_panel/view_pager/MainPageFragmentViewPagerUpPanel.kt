package com.setname.weather.mvp.views.welcome.adapter.up_panel.view_pager

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setname.weather.R
import com.setname.weather.mvp.models.adapter.welcome.up_panel.view_pager.MainModelUpPanel
import java.util.logging.Logger

class MainPageFragmentViewPagerUpPanel : Fragment() {

    val log by lazy {

        Logger.getLogger("MainPageFragment")

    }

    private lateinit var viewLocal: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewLocal = inflater.inflate(R.layout.adapter_weather_up_panel_view_pager_main_page, container, false)

        /*log.info("${arguments!!.getParcelable<Parcelable>("modelUpMain") == null}")*/

        /*val modelUpPanel: MainModelUpPanel =
            arguments!!.getParcelable<Parcelable>("modelUpMain") as MainModelUpPanel*/

        Logger.getLogger("ViewPager").info("0")


        return viewLocal
    }

    companion object {
        fun newInstance(modelUpPanel: MainModelUpPanel): Fragment {
            val fragment = MainPageFragmentViewPagerUpPanel()
            val args = Bundle()
            args.putParcelable("modelUpMain", modelUpPanel as Parcelable)
            fragment.arguments = args
            return fragment
        }

    }

}
