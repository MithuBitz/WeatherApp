package com.example.weatherforcast.ui.weather.current

import androidx.lifecycle.ViewModel
import com.example.weatherforcast.data.repository.ForecastRepository
import com.example.weatherforcast.internal.UnitSystem
import com.example.weatherforcast.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository
) : ViewModel() {

    private val unitSystem = UnitSystem.METRIC

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(isMetric)
    }
}