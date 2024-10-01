package com.example.savetier.usecase

import com.example.domain.model.TierDomainModel
import com.example.domain.repository.TierRepository
import com.example.domain.repository.UserRepository
import javax.inject.Inject

class SaveTierUseCase @Inject constructor(
    private val tierRepository: TierRepository,
    private val userRepository: UserRepository
) {

    suspend operator fun invoke(tier: TierDomainModel) {
        tierRepository.saveTier(tier = tier, username = userRepository.getCurrentUserCredentials())
    }
}