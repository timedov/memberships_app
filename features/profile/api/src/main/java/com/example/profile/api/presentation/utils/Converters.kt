package com.example.profile.api.presentation.utils

import com.example.profile.api.domain.model.UserDomainModel
import com.example.ui.model.UserUiModel
import com.example.ui.utils.timeToYear

fun UserDomainModel.toUiModel() =
    UserUiModel(
        username = username,
        imageUrl = imageUrl,
        joinedYear = joinedAt.timeToYear(),
        about = about
    )