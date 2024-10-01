package com.example.domain.model

import java.util.UUID

class SubscribeDomainModel(
    val id: String = UUID.randomUUID().toString(),
    val tierId: Long,
    val followed: String,
    val subscribedTo: String,
    val subscribedAt: Long,
    val status: SubscribeStatus,
    val expiredAt: Long
)