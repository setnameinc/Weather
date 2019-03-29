package com.setname.weather.mvp.views.welcome

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setname.weather.R
import com.setname.weather.mvp.adapters.welcome.WelcomeAdapter
import com.setname.weather.mvp.interfaces.welcome.WelcomeView
import com.setname.weather.mvp.interfaces.welcome.adapters.list_main.ListWelcome
import com.setname.weather.mvp.presenters.welcome.WelcomePresenter
import kotlinx.android.synthetic.main.fragment_welcome.*


class WelcomeFragment : Fragment(), WelcomeView {

    override fun getSupportFragmentManager(): FragmentManager = fragmentManager!!

    private lateinit var viewWelcome: View

    private var welcomePresenter: WelcomePresenter = WelcomePresenter(this)
    private lateinit var recyclerView: RecyclerView

    private lateinit var adapter: WelcomeAdapter
    private var listWelcome: ArrayList<ListWelcome> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewWelcome = inflater.inflate(R.layout.fragment_welcome, container, false)
        return viewWelcome
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()



        welcomePresenter.setForecast(18918)

    }

    private fun initRecyclerView() {

        recyclerView = fragment_welcome_rv
        adapter = WelcomeAdapter(listWelcome, welcomePresenter)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter


    }

    override fun setWeather(listView: List<ListWelcome>) {
        listWelcome.addAll(listView)
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}