package com.setname.weather.mvp.views.welcome

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.widget.SwipeRefreshLayout
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.logging.Logger

class WelcomeFragment : Fragment(), WelcomeView {

    private val ID_CITY:Long = 18918

    private val logger by lazy {

        Logger.getLogger("Welcome")

    }

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

        welcomePresenter.setForecast(ID_CITY)

    }

    private fun initRecyclerView() {

        recyclerView = fragment_welcome_rv
        adapter = WelcomeAdapter(listWelcome, welcomePresenter)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        fragment_welcome_swipe_refresh.setOnRefreshListener {

            CoroutineScope(Dispatchers.Default).launch {

                withContext(Dispatchers.IO){

                    welcomePresenter.setForecast(ID_CITY)

                }

                withContext(Dispatchers.Main){

                    fragment_welcome_swipe_refresh.isRefreshing = false

                }

            }

        }


    }

    override fun setWeather(listView: List<ListWelcome>) {

        listWelcome.clear()
        listWelcome.addAll(listView)

        CoroutineScope(Dispatchers.Main).launch{

            adapter.notifyDataSetChanged()

        }

    }


}