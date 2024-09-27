package com.example.domain.model

class UserDetailsDomainModel(
    val username: String = "",
    val imageUrl: String? = null,
    var subscribers: String = "",
    val joinedYear: Int = 0,
    val about: String = ""
)