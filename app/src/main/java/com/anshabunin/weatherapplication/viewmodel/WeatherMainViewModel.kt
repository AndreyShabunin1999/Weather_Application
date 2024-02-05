package com.anshabunin.weatherapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.anshabunin.sketchnote.entities.CurrentWeather
import com.anshabunin.sketchnote.entities.toResponseWeatherData
import com.anshabunin.weatherapplication.data.Resource
import com.anshabunin.weatherapplication.data.remote.ResponseWeatherData
import com.anshabunin.weatherapplication.data.remote.toCurrentWeather
import com.anshabunin.weatherapplication.domain.DbRepository
import com.anshabunin.weatherapplication.domain.NetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherMainViewModel(
    private val networkRepository: NetworkRepository,
    private val dbRepository: DbRepository
): ViewModel() {

    private val _weatherData = MutableLiveData<Resource<ResponseWeatherData>>()
    val weatherData: LiveData<Resource<ResponseWeatherData>> get() = _weatherData

    private val _dbInsertStatus = MutableLiveData<Boolean>()
    val dbInsertStatus: LiveData<Boolean> get() = _dbInsertStatus



    fun getWeatherData(town: String) {
        viewModelScope.launch {
            try {
                val data = withContext(Dispatchers.IO) {
                    networkRepository.getWeatherData(town)
                }
                _weatherData.value = Resource.success(data)
            } catch (e: Exception) {
                _weatherData.value = Resource.error(e.message ?: "An error occurred", null)
            }
        }
    }


    fun getSavedWeather() {
        viewModelScope.launch {
            try {
                val data = withContext(Dispatchers.IO) {
                    dbRepository.getWeather().toResponseWeatherData()
                }
                _weatherData.postValue(Resource.success(data))
            } catch (e: Exception) {
                _weatherData.postValue(Resource.error(e.message ?: "An error occurred", null))
            }
        }
    }

    fun insertWeatherDataIntoDb(responseWeatherData: ResponseWeatherData) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    dbRepository.insertWeather(responseWeatherData.toCurrentWeather())
                }
                _dbInsertStatus.value = true
            } catch (e: Exception) {
                _dbInsertStatus.value = false
            }
        }
    }


    companion object {
        fun factory(
            networkRepository: NetworkRepository,
            dbRepository: DbRepository
        ) = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return WeatherMainViewModel(networkRepository, dbRepository) as T
            }
        }
    }
}