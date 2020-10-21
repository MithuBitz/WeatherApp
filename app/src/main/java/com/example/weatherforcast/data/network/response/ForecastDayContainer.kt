package com.example.weatherforcast.data.network.response


import com.example.weatherforcast.data.db.entity.FutureWeatherEntry
import com.google.gson.annotations.SerializedName

data class ForecastDayContainer(
    @SerializedName("forecastday")
    val entries: List<FutureWeatherEntry>
)