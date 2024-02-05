package com.anshabunin.weatherapplication.domain

import com.anshabunin.sketchnote.dao.WeatherDao
import com.anshabunin.sketchnote.entities.CurrentWeather
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DbRepository @Inject constructor(
    private val dao: WeatherDao
){
    fun getWeather() = dao.getWeather()
    fun insertWeather(weather: CurrentWeather) = dao.insertWeather(weather)
}