package com.anshabunin.sketchnote.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.anshabunin.sketchnote.entities.CurrentWeather

@Dao
interface WeatherDao {
    @Query("SELECT * FROM current_weather LIMIT 1")
    fun getWeather(): CurrentWeather

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(currentWeather: CurrentWeather)
}