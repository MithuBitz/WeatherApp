package com.example.weatherforcast

import android.app.Application
import android.content.Context
import androidx.preference.PreferenceManager
import com.example.weatherforcast.data.db.ForcastDatabase
import com.example.weatherforcast.data.network.*
import com.example.weatherforcast.data.provider.LocationProvider
import com.example.weatherforcast.data.provider.LocationProviderImpl
import com.example.weatherforcast.data.provider.UnitProvider
import com.example.weatherforcast.data.provider.UnitProviderImpl
import com.example.weatherforcast.data.repository.ForecastRepository
import com.example.weatherforcast.data.repository.ForecastRepositoryImpl
import com.example.weatherforcast.ui.weather.current.CurrentWeatherViewModelFactory
import com.google.android.gms.location.LocationServices
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.Provider
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ForecastApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ForecastApplication))

        bind() from singleton { ForcastDatabase(instance()) }
        bind() from singleton { instance<ForcastDatabase>().currentWeatherDao() }
        bind() from singleton { instance<ForcastDatabase>().weatherLocationDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { ApixuWeatherApiService(instance()) }
        bind<WeatherNetworkDataSource>() with singleton { WeatherNetworkDataSourceImpl(instance()) }
        bind() from provider { LocationServices.getFusedLocationProviderClient(instance<Context>()) }
        bind<LocationProvider>() with singleton { LocationProviderImpl(instance(), instance()) }
        bind<ForecastRepository>() with singleton { ForecastRepositoryImpl(instance(), instance(), instance(), instance()) }
        bind<UnitProvider>() with singleton { UnitProviderImpl(instance()) }
        bind() from provider { CurrentWeatherViewModelFactory(instance(), instance()) }
    }

        override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
    }
}