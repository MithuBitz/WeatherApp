package com.example.weatherforcast.data.repository

import androidx.lifecycle.LiveData
import com.example.weatherforcast.data.db.entity.WeatherLocation
import com.example.weatherforcast.data.db.unitlocalized.current.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry>
    suspend fun getWeatherLocation(): LiveData<WeatherLocation>
}