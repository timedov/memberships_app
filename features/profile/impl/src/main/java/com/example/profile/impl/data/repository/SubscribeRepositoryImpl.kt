package com.example.profile.impl.data.repository

import com.example.profile.api.domain.repository.SubscribeRepository
import com.example.data.api.subscribe.datasource.SubscribeDataSource
import com.example.profile.api.domain.model.SubscribeDomainModel
import com.example.profile.impl.data.utils.toDataModel
import javax.inject.Inject

class SubscribeRepositoryImpl @Inject constructor(
    private val subscribeDataSource: SubscribeDataSource,
) : SubscribeRepository {

    override suspend fun subscribeToUser(subscribe: SubscribeDomainModel) {
        subscribeDataSource.subscribeToUser(subscribe.toDataModel())
    }

    override suspend fun unsubscribeFromUser(subscribedTo: String, followed: String) {
        subscribeDataSource.unsubscribeFromUser(subscribedTo = subscribedTo, followed = followed)
    }

    override suspend fun getUserSubscribersCount(username: String): Int =
        subscribeDataSource.getUserSubscribersCount(username)

    override suspend fun isUserSubscribed(subscribedTo: String, followed: String): Boolean =
        subscribeDataSource.isUserSubscribed(followed = followed, subscribedTo = subscribedTo)
}
