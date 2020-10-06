package com.example.weatherforcast.data.repository

import androidx.lifecycle.LiveData
import com.example.weatherforcast.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry

class ForecastRepositoryImpl : ForecastRepository {
    override suspend fun getCurrentWeather(metric: Boolean): LiveData<UnitSpecificCurrentWeatherEntry> {
        TODO("Not yet implemented")
    }
}