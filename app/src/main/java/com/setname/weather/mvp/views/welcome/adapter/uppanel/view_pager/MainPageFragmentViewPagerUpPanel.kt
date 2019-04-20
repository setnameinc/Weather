package com.setname.weather.mvp.views.welcome.adapter.uppanel.view_pager

import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.setname.weather.R
import com.setname.weather.mvp.interfaces.welcome.adapters.up_panel.view_pager.UpdateViewPager
import com.setname.weather.mvp.models.adapter.welcome.up_panel.view_pager.MainModelUpPanel
import kotlinx.android.synthetic.main.adapter_weather_up_panel_view_pager_main_page.*
import kotlinx.android.synthetic.main.adapter_weather_up_panel_view_pager_main_page.view.*
import java.util.logging.Logger

class MainPageFragmentViewPagerUpPanel : Fragment(), UpdateViewPager {

    override fun update(model: Any) {
        setView(model as MainModelUpPanel)
    }

    private lateinit var viewLocal: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewLocal = inflater.inflate(R.layout.adapter_weather_up_panel_view_pager_main_page, container, false)

        val mainModelUpPanel: MainModelUpPanel =
            arguments!!.getParcelable<Parcelable>("modelUpMain") as MainModelUpPanel

        setView(mainModelUpPanel)

        return viewLocal
    }

    private fun setView(modelUpPanel: MainModelUpPanel) {

        val anim = AnimationUtils.loadAnimation(context, R.anim.item_animation_appearance)

        viewLocal.apply {

            startAnimation(anim)

            adapter_weather_up_panel_view_pager_main_page_desc.text =
                modelUpPanel.desc.replaceFirst(modelUpPanel.desc[0], modelUpPanel.desc[0].toUpperCase())

            adapter_weather_up_panel_view_pager_main_page_current_temp.text = Math.round(modelUpPanel.temp).toString()

        }

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

    val log by lazy {

        Logger.getLogger("MainPageFragment")

    }

}
