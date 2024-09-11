package com.example.network.datasource.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class PostResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("image")
    val image: String,
    @SerialName("category")
    val category: String,
    @SerialName("thumbnailUrl")
    val thumbnailUrl: String,
    @SerialName("videosCount")
    val videosCount: Int,
    @SerialName("postsCount")
    val postsCount: Int
)