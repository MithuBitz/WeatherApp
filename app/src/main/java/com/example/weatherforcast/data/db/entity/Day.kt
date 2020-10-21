package com.example.weatherforcast.data.db.entity


import androidx.room.Embedded
import com.example.weatherforcast.data.db.entity.Condition
import com.google.gson.annotations.SerializedName

data class Day(
    val avghumidity: Int,
    @SerializedName("avgtemp_c")
    val avgtempC: Int,
    @SerializedName("avgtemp_f")
    val avgtempF: Int,
    @SerializedName("avgvis_km")
    val avgvisKm: Int,
    @SerializedName("avgvis_miles")
    val avgvisMiles: Int,
    @Embedded(prefix = "condition_")
    val condition: Condition,
    @SerializedName("daily_chance_of_rain")
    val dailyChanceOfRain: String,
    @SerializedName("daily_chance_of_snow")
    val dailyChanceOfSnow: String,
    @SerializedName("daily_will_it_rain")
    val dailyWillItRain: Int,
    @SerializedName("daily_will_it_snow")
    val dailyWillItSnow: Int,
    @SerializedName("maxtemp_c")
    val maxtempC: Int,
    @SerializedName("maxtemp_f")
    val maxtempF: Double,
    @SerializedName("maxwind_kph")
    val maxwindKph: Int,
    @SerializedName("maxwind_mph")
    val maxwindMph: Double,
    @SerializedName("mintemp_c")
    val mintempC: Double,
    @SerializedName("mintemp_f")
    val mintempF: Double,
    @SerializedName("totalprecip_in")
    val totalprecipIn: Double,
    @SerializedName("totalprecip_mm")
    val totalprecipMm: Int,
    val uv: Int
)