package com.example.domain.model

import java.util.UUID

class FavoriteDomainModel(
    val id: String = UUID.randomUUID().toString(),
    val postId: Long,
    val username: String,
    val isFavorite: Boolean
)