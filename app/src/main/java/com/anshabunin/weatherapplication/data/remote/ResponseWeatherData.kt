package com.anshabunin.weatherapplication.data.remote

import com.anshabunin.sketchnote.entities.CurrentWeather
import com.google.gson.annotations.SerializedName


data class ResponseWeatherData(
    @SerializedName("current")
    val current: Current?,
    @SerializedName("location")
    val location: Location?
)

fun ResponseWeatherData.toCurrentWeather(): CurrentWeather {
    return CurrentWeather(
        name = this.location?.name ?: "",
        country = this.location?.country ?: "",
        region = this.location?.region ?: "",
        text = this.current?.condition?.text ?: "",
        icon = this.current?.condition?.icon ?: "",
        pressureMb = this.current?.pressureMb ?: 0.0,
        tempC = this.current?.tempC ?: 0.0,
        uv = this.current?.uv ?: 0.0,
        humidity = this.current?.humidity ?: 0,
        windKph = this.current?.windKph ?: 0.0,
        precipMm = this.current?.precipMm ?: 0.0
    )
}
