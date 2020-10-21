package com.example.weatherforcast.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherforcast.data.db.entity.CurrentWeatherEntry
import com.example.weatherforcast.data.db.entity.WeatherLocation

@Database(
    entities = [CurrentWeatherEntry::class, WeatherLocation::class],
    version = 1
)

@TypeConverters(LocalDateConverter::class)
abstract class ForcastDatabase : RoomDatabase(){
    abstract fun currentWeatherDao() : CurrentWeatherDao
    abstract fun futureWeatherDao() : FutureWeatherDao
    abstract fun weatherLocationDao() : WeatherLocationDao

    companion object {
        @Volatile private var instance: ForcastDatabase? = null
        private val LOCK = Any() //This will takeing care of not to run two thread at same time

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, ForcastDatabase::class.java, "forecast.db")
                .build()
    }

}