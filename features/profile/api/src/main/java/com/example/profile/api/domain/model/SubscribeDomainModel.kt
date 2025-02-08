package com.example.profile.api.domain.model

import java.util.UUID

class SubscribeDomainModel(
    val id: String = UUID.randomUUID().toString(),
    val followed: String,
    val subscribedTo: String,
    val subscribedAt: Long = System.currentTimeMillis()
)