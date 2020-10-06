package com.example.weatherforcast.data.repository

import androidx.lifecycle.LiveData
import com.example.weatherforcast.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry

interface ForecastRepository {
    suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry>
}