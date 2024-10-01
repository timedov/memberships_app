package com.example.network.remote.datasource

import com.example.network.remote.datasource.requests.SaveTierRequest
import com.example.network.remote.datasource.responses.TierResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TierApi {

    @GET("/tiers")
    suspend fun getTiersByUser(
        @Query("username") username: String
    ): List<TierResponse>

    @GET("/tiers")
    suspend fun getTierById(
        @Query("id") id: Long
    ): TierResponse

    @POST("/tiers")
    suspend fun saveTier(
        @Body saveTierRequest: SaveTierRequest
    )
}