package com.anshabunin.sketchnote.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anshabunin.weatherapplication.data.remote.Condition
import com.anshabunin.weatherapplication.data.remote.Current
import com.anshabunin.weatherapplication.data.remote.Location
import com.anshabunin.weatherapplication.data.remote.ResponseWeatherData

@Entity(tableName = "current_weather")
data class CurrentWeather(
    @PrimaryKey
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "country")
    val country: String = "",
    @ColumnInfo(name = "region")
    val region: String = "",
    @ColumnInfo(name = "text")
    val text: String = "",
    @ColumnInfo(name = "icon")
    val icon: String = "",
    @ColumnInfo(name = "pressureMb")
    val pressureMb: Double = 0.0,
    @ColumnInfo(name = "tempC")
    val tempC: Double = 0.0,
    @ColumnInfo(name = "uv")
    val uv: Double = 0.0,
    @ColumnInfo(name = "humidity")
    val humidity: Int = 0,
    @ColumnInfo(name = "windKph")
    val windKph: Double = 0.0,
    @ColumnInfo(name = "precipMm")
    val precipMm: Double = 0.0
)

fun CurrentWeather.toResponseWeatherData(): ResponseWeatherData {
    return ResponseWeatherData(
        current = Current(
            condition = Condition(
                code = 0,
                icon = this.icon,
                text = this.text
            ),
            pressureMb = this.pressureMb,
            tempC = this.tempC,
            uv = this.uv,
            humidity = this.humidity,
            windKph = this.windKph,
            precipMm = this.precipMm,
            cloud = 0,
            feelslikeC = 0.0,
            feelslikeF = 0.0,
            gustKph = 0.0,
            gustMph = 0.0,
            isDay = 0,
            lastUpdated = "",
            lastUpdatedEpoch = 0,
            precipIn = 0.0,
            pressureIn = 0.0,
            tempF = 0.0,
            visKm = 0.0,
            visMiles = 0.0,
            windDegree = 0,
            windDir = "",
            windMph = 0.0),
        location = Location(
            country = this.country,
            lat = 0.0,
            localtime = "",
            localtimeEpoch = 0,
            lon = 0.0,
            name = this.name,
            region = this.region,
            tzId = ""
        )
    )
}
