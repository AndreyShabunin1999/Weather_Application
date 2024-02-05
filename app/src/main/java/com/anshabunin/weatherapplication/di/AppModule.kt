package com.anshabunin.weatherapplication.di

import android.content.Context
import androidx.room.Room
import com.anshabunin.sketchnote.database.WeatherDatabase
import com.anshabunin.sketchnote.entities.CurrentWeather
import com.anshabunin.weatherapplication.data.WeatherService
import com.anshabunin.weatherapplication.domain.Impl.NetworkRepositoryImpl
import com.anshabunin.weatherapplication.domain.NetworkRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideApi(gson: Gson): WeatherService {
        return Retrofit.Builder()
            .baseUrl("http://api.weatherapi.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(WeatherService::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkRepository(weatherService: WeatherService): NetworkRepository {
        return NetworkRepositoryImpl(weatherService)
    }

    @Provides
    @Singleton
    fun provideNotesDatabase(@ApplicationContext context: Context): WeatherDatabase {
        return Room.databaseBuilder(
            context,
            WeatherDatabase::class.java, "weather_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(db: WeatherDatabase) = db.weatherDao()

    @Provides
    fun provideEntity() = CurrentWeather()
}