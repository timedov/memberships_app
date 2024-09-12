package com.example.network.remote.datasource.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class PostResponse(
    @SerialName("id")
    val id: Long = 0L,
    @SerialName("title")
    val title: String?,
    @SerialName("image")
    val image: String?,
    @SerialName("category")
    val category: String?,
    @SerialName("videosCount")
    val videosCount: Int = 0,
    @SerialName("postsCount")
    val postsCount: Int = 0,
)