package com.example.network.remote.datasource.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class PostDataResponse(
    @SerialName("id")
    val id: Long = 0L,
    @SerialName("title")
    val title: String?,
    @SerialName("content")
    val content: String?,
    @SerialName("contentType")
    val contentType: Int = 0,
    @SerialName("category")
    val category: String?,
    @SerialName("posted")
    val postedTimestamp: Long = 0,
    @SerialName("author")
    val authorName: String?,
    @SerialName("body")
    val body: String?,
    @SerialName("isPaid")
    val isPaid: Boolean = false
)