package com.lubnamariyam.soho.viewModel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lubnamariyam.soho.model.Info
import com.lubnamariyam.soho.model.RandomUserResponse
import com.lubnamariyam.soho.model.weather.*
import com.lubnamariyam.soho.network.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeViewModel : ViewModel() {

    var productResponse: RandomUserResponse by mutableStateOf(
        RandomUserResponse(info = Info(0,0,"",""),
            listOf())
    )
    var weatherResponse: WeatherResponse by mutableStateOf(
        WeatherResponse(
            "", Clouds(0), 0, Coord(0.0, 0.0), 0, 0,
            Main(0.0, 0, 0, 0, 0, 0.0, 0.0, 0.0), "",
            Sys("", 0, 0), 0, 0, listOf(), Wind(0, 0.0, 0.0)
        )
    )
    var weatherApiKey = "4c9e5c9da50f2e249be0f6f60b235894"


    fun getWeatherData(lat: Double, lon: Double) {
        val apiService = RetrofitService.ApiService.getInstance()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val url =
                    "https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${weatherApiKey}"
                val weatherData = apiService.getCurrentWeather(url)
                weatherResponse = weatherData.body()!!
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    var response = MutableLiveData<RandomUserResponse>()

    var errorMessage: String by mutableStateOf("")
    fun getProductList() {
        viewModelScope.launch {
            val apiService = RetrofitService.ApiService.getInstance()
            try {
                val productList = apiService.getProduct()
                productResponse = productList
                //println("Hii " + productResponse.results.size)

            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

}