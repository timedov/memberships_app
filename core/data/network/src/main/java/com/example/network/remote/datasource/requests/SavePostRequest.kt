package com.example.network.remote.datasource.requests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SavePostRequest(
    @SerialName("title")
    val title: String,
    @SerialName("category")
    val category: String,
    @SerialName("author")
    val author: String,
    @SerialName("body")
    val body: String,
    @SerialName("requires_subscription")
    val requiresSubscription: Boolean
)