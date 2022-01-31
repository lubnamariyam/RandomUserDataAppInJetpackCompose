package com.lubnamariyam.soho.network

import com.lubnamariyam.soho.model.randomuser.RandomUserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserAPI {
    @GET("/api/")
    suspend fun getAllUsers(
        @Query("results") results: Int,
    ): RandomUserResponse
}
