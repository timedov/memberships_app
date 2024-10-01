package com.example.savetier.usecase

import com.example.domain.model.TierDomainModel
import com.example.domain.repository.TierRepository
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveTierUseCase @Inject constructor(
    private val tierRepository: TierRepository,
    private val userRepository: UserRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(tier: TierDomainModel) {
        withContext(coroutineDispatcher) {
            tierRepository.saveTier(
                tier = tier,
                username = userRepository.getCurrentUserCredentials()
            )
        }
    }
}