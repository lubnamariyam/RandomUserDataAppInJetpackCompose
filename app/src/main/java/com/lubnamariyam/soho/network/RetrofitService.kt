package com.lubnamariyam.soho.network

import com.lubnamariyam.soho.model.randomuser.RandomUserResponse
import com.lubnamariyam.soho.model.weather.WeatherResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

class RetrofitService {
    interface ApiService {
        @GET("/api/?results=25")
        suspend fun getProduct(): RandomUserResponse

        @GET
        suspend fun getCurrentWeather(
            @Url url: String
        ): Response<WeatherResponse>

        companion object {
            private var apiService: ApiService? = null
            private var baseUrl = "https://randomuser.me"
            fun getInstance(): ApiService {
                if (apiService == null) {
                    apiService = Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(ApiService::class.java)
                }
                return apiService!!
            }
        }
    }
}