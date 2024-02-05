package com.anshabunin.weatherapplication.domain

import com.anshabunin.weatherapplication.data.remote.ResponseWeatherData

interface NetworkRepository {
    suspend fun getWeatherData(town: String): ResponseWeatherData
}