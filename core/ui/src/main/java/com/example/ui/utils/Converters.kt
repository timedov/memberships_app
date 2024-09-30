package com.example.ui.utils

import com.example.domain.model.TierDomainModel
import com.example.domain.model.UserDetailsDomainModel
import com.example.ui.model.TierUiModel
import com.example.ui.model.UserDetailsUiModel

fun TierDomainModel.toUiModel() =
    TierUiModel(
        id = id,
        name = name,
        price = "$$price",
        description = description
    )

fun TierUiModel.toDomainModel() =
    TierDomainModel(
        id = id,
        name = name,
        price = price.removePrefix("$").toDouble(),
        description = description
    )

fun UserDetailsDomainModel.toUiModel() =
    UserDetailsUiModel(
        username = username,
        imageUrl = imageUrl,
        subscribers = subscribers,
        joinedYear = joinedYear,
        about = about
    )