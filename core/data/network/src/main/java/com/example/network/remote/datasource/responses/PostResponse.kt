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
    @SerialName("body")
    val body: String?,
    @SerialName("category")
    val category: String?,
    @SerialName("views")
    val views: Int = 0,
    @SerialName("posted")
    val postedTimestamp: Long = 0,
    @SerialName("author")
    val authorName: String?,
    @SerialName("requires_subscription")
    val requiresSubscription: Boolean = false
)

fun PostResponse.isResponseEmpty() = id == 0L
            && title.isNullOrEmpty()
            && image.isNullOrEmpty()
            && category.isNullOrEmpty()