package com.example.subscribe.usecase

import android.icu.util.Calendar
import com.example.domain.model.SubscribeDomainModel
import com.example.domain.model.SubscribeStatus
import com.example.domain.repository.SubscribeRepository
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SubscribeUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val subscribeRepository: SubscribeRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(tierId: Long, subscribedTo: String) {
        withContext(coroutineDispatcher) {
            val currentUserUsername = userRepository.getCurrentUserCredentials()
            val calendar = Calendar.getInstance()
            subscribeRepository.subscribeToTier(
                SubscribeDomainModel(
                    tierId = tierId,
                    followed = currentUserUsername,
                    subscribedTo = subscribedTo,
                    subscribedAt = calendar.timeInMillis,
                    status = SubscribeStatus.ACTIVE,
                    expiredAt = calendar.apply { add(Calendar.MONTH, 1) }.timeInMillis
                )
            )
        }
    }
}