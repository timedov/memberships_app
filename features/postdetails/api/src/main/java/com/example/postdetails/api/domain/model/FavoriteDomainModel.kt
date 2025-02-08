package com.example.postdetails.api.domain.model

import java.util.UUID

class FavoriteDomainModel(
    val id: String = UUID.randomUUID().toString(),
    val postId: String,
    val username: String,
    val isFavorite: Boolean
)