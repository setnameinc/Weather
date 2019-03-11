package com.setname.weather.mvp.views.welcome

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setname.weather.R
import com.setname.weather.mvp.adaters.welcome.WelcomeAdapter
import com.setname.weather.mvp.interfaces.welcome.WelcomeView
import com.setname.weather.mvp.interfaces.welcome.adapter.list.ListWelcome
import com.setname.weather.mvp.models.adapter.welcome.weather_main.ModelWeatherMain
import com.setname.weather.mvp.presenters.welcome.WelcomePresenter
import kotlinx.android.synthetic.main.fragment_welcome.*
import com.setname.weather.mvp.models.retrofit.ModelResponse
import java.util.logging.Logger


class WelcomeFragment : Fragment(), WelcomeView {

    private lateinit var viewWelcome: View
    private var welcomePresenter: WelcomePresenter = WelcomePresenter(this)

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: WelcomeAdapter
    private var list: ArrayList<ListWelcome> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewWelcome = inflater.inflate(R.layout.fragment_welcome, container, false)
        return viewWelcome
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        welcomePresenter.setForecast(18918)

    }

    fun initRecyclerView() {

        recyclerView = fragment_welcome_rv
        adapter = WelcomeAdapter(list)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter


    }

    override fun showRecyclerViewForecast(modelResponse: ModelResponse?, modelWeatherMain:ModelWeatherMain?) {
        list.add(modelWeatherMain!!)
//        list.addAll(modelResponse!!.list)
        adapter.notifyDataSetChanged()
//        Logger.getLogger("Welcome").info(modelResponse!!.list[0].weather.joinToString())
    }

    override fun showCurrentDayForecast() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}