package com.setname.weather.mvp.retrofit

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.setname.weather.mvp.models.retrofit.ModelResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPIService {

    @GET("data/2.5/forecast")
    fun getForecastByCityId(
        @Query("id") id: Long,
        @Query("appid") apiId: String = "fabd53f6a71e2f82c551ff0c5eda930b",
        @Query("units") units: String = "metric"
    ): Deferred<Response<ModelResponse>>


    companion object {

        val retrofit: WeatherAPIService by lazy {

            fun makeRetrofit(): Retrofit = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()

            return@lazy makeRetrofit().create(WeatherAPIService::class.java)

        }
    }

}