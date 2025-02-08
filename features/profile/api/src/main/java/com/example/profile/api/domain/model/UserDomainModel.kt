package com.example.profile.api.domain.model

class UserDomainModel(
    val username: String = "",
    val imageUrl: String? = null,
    val joinedAt: Long = 0,
    val about: String = ""
)