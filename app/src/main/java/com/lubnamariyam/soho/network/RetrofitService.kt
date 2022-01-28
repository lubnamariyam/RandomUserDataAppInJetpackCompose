package com.lubnamariyam.soho.network

import com.lubnamariyam.soho.model.RandomUserResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class RetrofitService {
    interface ApiService {
        @GET("/api/?results=25")
        suspend fun getProduct() : RandomUserResponse


        companion object {
            var apiService: ApiService? = null
            var baseUrl = "https://randomuser.me"
            fun getInstance() : ApiService {
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