package com.lubnamariyam.soho.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import com.lubnamariyam.soho.Utils.SohoConstants
import com.lubnamariyam.soho.model.weather.*
import com.lubnamariyam.soho.network.RetrofitService
import com.lubnamariyam.soho.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: Repository
) : ViewModel() {
    val getAllUsers = repository.getAllRandomUsers()
    var weatherResponse: WeatherResponse by mutableStateOf(
        WeatherResponse(
            "", Clouds(0), 0, Coord(0.0, 0.0), 0, 0,
            Main(0.0, 0, 0, 0, 0, 0.0, 0.0, 0.0), "",
            Sys("", 0, 0), 0, 0, listOf(), Wind(0, 0.0, 0.0)
        )
    )

    private var errorMessage: String by mutableStateOf("")

    fun getWeatherData(lat: Double, lon: Double) {
        val apiService = RetrofitService.ApiService.getInstance()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val url =
                    "https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${SohoConstants.weatherApiKey}"
                val weatherData = apiService.getCurrentWeather(url)
                weatherResponse = weatherData.body()!!
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}