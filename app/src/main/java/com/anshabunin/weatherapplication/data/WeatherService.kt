package com.anshabunin.weatherapplication.data

import com.anshabunin.weatherapplication.data.remote.ResponseWeatherData
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("v1/current.json?key=36abc94f18bb4776b22124354233108&aqi=no&lang=ru")
    suspend fun getData(@Query("q") requestName: String): ResponseWeatherData
}