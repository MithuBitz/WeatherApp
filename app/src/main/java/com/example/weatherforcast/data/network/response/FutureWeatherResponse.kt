package com.example.weatherforcast.data.network.response


import com.example.weatherforcast.data.db.entity.WeatherLocation
import com.google.gson.annotations.SerializedName

data class FutureWeatherResponse(
    @SerializedName("forecast")
    val futureWeatherEntries: ForecastDayContainer,
    val location: WeatherLocation
)