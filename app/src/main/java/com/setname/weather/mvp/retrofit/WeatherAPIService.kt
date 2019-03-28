package com.setname.weather.mvp.retrofit

import com.setname.weather.mvp.models.retrofit.ModelResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPIService {

    @GET("data/2.5/forecast")
    fun getForecastByCityId(@Query("id") id:Long,
                            @Query("appid") apiId:String = "fabd53f6a71e2f82c551ff0c5eda930b",
                            @Query("units") units:String = "metric"): Call<ModelResponse>


    companion object {

        val retrofit: WeatherAPIService by lazy {

            fun makeRetrofit(): Retrofit = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return@lazy makeRetrofit().create(WeatherAPIService::class.java)

        }
    }

}