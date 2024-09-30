package com.example.network.remote.datasource.requests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SaveTierRequest(
    @SerialName("name")
    val name: String,
    @SerialName("author")
    val author: String,
    @SerialName("price")
    val price: Double,
    @SerialName("description")
    val description: String
)