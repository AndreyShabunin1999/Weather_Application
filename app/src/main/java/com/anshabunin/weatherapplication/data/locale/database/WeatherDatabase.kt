package com.anshabunin.sketchnote.database

import androidx.room.Database
import androidx.room.DatabaseView
import androidx.room.RoomDatabase
import com.anshabunin.sketchnote.dao.WeatherDao
import com.anshabunin.sketchnote.entities.CurrentWeather

@Database(entities = [CurrentWeather::class], version = 1)
@DatabaseView
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}