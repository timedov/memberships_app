package com.example.subscribe.usecase

import android.icu.util.Calendar
import com.example.domain.model.SubscribeModel
import com.example.domain.model.SubscribeStatus
import com.example.domain.repository.SubscribeRepository
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class SubscribeUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val subscribeRepository: SubscribeRepository
) {

    suspend operator fun invoke(tierId: Long, subscribedTo: String) {
        val currentUserUsername = userRepository.getCurrentUserCredentials()
        val calendar = Calendar.getInstance()
        subscribeRepository.subscribeToTier(
            SubscribeModel(
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