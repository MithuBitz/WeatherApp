package com.example.weatherforcast.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherforcast.data.db.entity.CURRENT_WEATHER_ID
import com.example.weatherforcast.data.db.entity.CurrentWeatherEntry
import com.example.weatherforcast.data.db.unitlocalized.current.ImperialCurrentWeatherEntry
import com.example.weatherforcast.data.db.unitlocalized.current.MetricCurrentWeatherEntry

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) //Replace the old one with new weatherEntry bcoz the id always same
    fun upsert(weatherEntry: CurrentWeatherEntry) //Update and Insert data from the database

    @Query("select * from current_weather where id = $CURRENT_WEATHER_ID")
    fun getWeatherMetric(): LiveData<MetricCurrentWeatherEntry>

    @Query("select * from current_weather where id = $CURRENT_WEATHER_ID")
    fun getWeatherImperial(): LiveData<ImperialCurrentWeatherEntry>


}