package com.example.weatherforcast.data.network.response

import com.example.weatherforcast.data.db.entity.CurrentWeatherEntry
import com.example.weatherforcast.data.db.entity.WeatherLocation
import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    val location: WeatherLocation,
    @SerializedName("current") //This is used to annotate the current object with our currentWeatherEntry
    val currentWeatherEntry: CurrentWeatherEntry
)