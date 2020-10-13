package com.example.weatherforcast.ui.weather.current

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherforcast.R
import com.example.weatherforcast.data.network.ApixuWeatherApiService
import com.example.weatherforcast.data.network.ConnectivityInterceptorImpl
import com.example.weatherforcast.data.network.WeatherNetworkDataSourceImpl
import com.example.weatherforcast.internal.glide.GlideApp
import com.example.weatherforcast.ui.base.ScopeFragment
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class CurrentWeatherFragment : ScopeFragment(), KodeinAware{

    override val kodein by closestKodein() //closestKodein() is pointing to nearest kodein in this case it was ForcastApplication
    //private val viewModelFactory: CurrentWeatherViewModelFactory by instance()
    private val viewModelFactory by instance<CurrentWeatherViewModelFactory>()

    /*Delete those line bcoz we use kodeing dependency injection
    companion object {
        fun newInstance() = CurrentWeatherFragment()
    }*/

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(CurrentWeatherViewModel::class.java)

        bindUI()

        /*// this line of code not required in case of viewModelFactory implementation with kodein dependency injection
        val apiService = ApixuWeatherApiService(ConnectivityInterceptorImpl(this.requireContext()))
        val weatherNetworkDataSource = WeatherNetworkDataSourceImpl(apiService)

        weatherNetworkDataSource.downloadedCurrentWeather.observe(this, Observer {
            tv1.text = it.toString()
        })

        GlobalScope.launch(Dispatchers.Main) {
            weatherNetworkDataSource.fetchCurrentWeather("Golaghat", "en")

        }*/
    }

    private fun bindUI() = launch {
        val currentWeather = viewModel.weather.await()

        currentWeather.observe(viewLifecycleOwner, Observer {
            //null check for avoid error to show the toString is used in a null object
            if (it == null) return@Observer

            //Hide the loading prograss bar and loading text from the layout
            groupLoading.visibility = View.GONE

            updateLocation("Jorhat")
            updateDateToToday()
            updateTemperature(it.temperature, it.feelsLikeTemperature)
            updateCondition(it.conditionText)
            updatePrecipitation(it.precipitationVolume)
            updateWind(it.windDirection, it.windSpeed)
            updateVisibility(it.visibilityDistance)

            GlideApp.with(this@CurrentWeatherFragment)
                .load("http:${it.conditionIconUrl}")
                .into(imageView_ConditionIcon)
        })
    }

    //For UnitAbbriviation
    private fun chooseLocalizedUnitAbbreviation(metric: String, imperial: String): String {
        return if (viewModel.isMetric) metric else imperial
    }

    //Function to display location on the ActionBar
    private fun updateLocation(location: String) {
        (activity as? AppCompatActivity)?.supportActionBar?.title = location
    }

    //Function to display today on the ActionBar
    private fun updateDateToToday() {
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = "Today"
    }

    private fun updateTemperature(temperature: Double, feelsLike: Double){
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("°C", "°F")
        textView_Temperature.text = "$temperature$unitAbbreviation"
        textView_feels_like_temperature.text = "Feels like $feelsLike$unitAbbreviation"
    }

    private fun updateCondition(condition: String) {
        textView_condition.text = condition
    }

    private fun updatePrecipitation(precipitationVolume: Double) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("mm", "in")
        textView_precipitation.text = "Precipitation: $precipitationVolume $unitAbbreviation"
    }

    private fun updateWind(windDirection: String, windSpeed: Double) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("kph", "mph")
        textView_wind.text = "Wind: $windDirection, $windSpeed $unitAbbreviation"
    }

    private fun updateVisibility(visibilityDistance: Double) {
        val unitAbbreviation = chooseLocalizedUnitAbbreviation("km", "mi")
        textView_precipitation.text = "Visibility: $visibilityDistance $unitAbbreviation"
    }

}