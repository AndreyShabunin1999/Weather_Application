package com.anshabunin.weatherapplication.domain.Impl

import com.anshabunin.weatherapplication.data.WeatherService
import com.anshabunin.weatherapplication.data.remote.ResponseWeatherData
import com.anshabunin.weatherapplication.domain.NetworkRepository
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(
    private val api: WeatherService
) : NetworkRepository {
    override suspend fun getWeatherData(town: String): ResponseWeatherData {
        return api.getData(town)
    }
}