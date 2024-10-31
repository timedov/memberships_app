package com.example.network.remote.datasource

import com.example.network.remote.datasource.responses.PostDataResponse
import com.example.network.remote.datasource.responses.PostResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface PostApi {

    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id: Long): PostDataResponse

    @GET("posts")
    suspend fun getPosts(@Query("page") page: Int, @Query("size") size: Int): List<PostResponse>

    @GET("posts")
    suspend fun getPostsByTier(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("tier") tier: Int
    ): List<PostResponse>

    @GET("posts")
    fun getPostsByAuthor(
        @Query("page") page: Int,
        @Query("size") size: Int,
        @Query("author") author: String
    ): List<PostResponse>

    @Multipart
    @POST("posts/save")
    suspend fun savePost(
        @Part("postData") postData: RequestBody,
        @Part("content") content: MultipartBody.Part?
    ): Response<Unit>
}