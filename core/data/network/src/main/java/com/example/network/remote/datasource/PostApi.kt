package com.example.network.remote.datasource

import com.example.network.remote.datasource.responses.PostResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostApi {

    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id: Long): PostResponse

    @GET("posts")
    suspend fun getPosts(@Query("page") page: Int, @Query("size") size: Int): List<PostResponse>

    @GET("posts")
    suspend fun getPostsByTier(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("tier") tier: Int
    ): List<PostResponse>
}