package com.example.weatherforcast.data.network.response

import com.example.weatherforcast.data.db.entity.CurrentWeatherEntry
import com.example.weatherforcast.data.db.entity.Location
import com.google.gson.annotations.SerializedName

data class CurrentWeatherResponse(
    @SerializedName("current") //This is used to annotate the current object with our currentWeatherEntry
    val currentWeatherEntry: CurrentWeatherEntry,
    val location: Location
)