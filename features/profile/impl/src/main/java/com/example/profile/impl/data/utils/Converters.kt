package com.example.profile.impl.data.utils

import com.example.profile.api.domain.model.SubscribeDomainModel
import com.example.profile.api.domain.model.UserDomainModel
import com.example.data.api.subscribe.model.SubscribeDataModel
import com.example.data.api.user.model.UserDataModel

internal fun SubscribeDomainModel.toDataModel(): SubscribeDataModel =
    SubscribeDataModel(
        id = id,
        followed = followed,
        subscribedTo = subscribedTo,
        subscribedAt = subscribedAt
    )

internal fun UserDataModel.toDomainModel(): UserDomainModel =
    UserDomainModel(
        username = username,
        imageUrl = imageUrl,
        joinedAt = joinedAt,
        about = about
    )