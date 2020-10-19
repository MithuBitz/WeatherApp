package com.example.weatherforcast.ui.weather.current

import androidx.lifecycle.ViewModel
import com.example.weatherforcast.data.provider.UnitProvider
import com.example.weatherforcast.data.repository.ForecastRepository
import com.example.weatherforcast.internal.UnitSystem
import com.example.weatherforcast.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository,
    unitProvider: UnitProvider
) : ViewModel() {

    private val unitSystem = unitProvider.getUnitSystem()

    val isMetric: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weather by lazyDeferred {
        forecastRepository.getCurrentWeather(isMetric)
    }

    val weatherLocation by lazyDeferred {
        forecastRepository.getWeatherLocation()
    }
}