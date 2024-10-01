package com.example.savetier.usecase

import com.example.domain.model.TierDomainModel
import com.example.domain.repository.TierRepository
import javax.inject.Inject

class GetTierUseCase @Inject constructor(
    private val tierRepository: TierRepository
) {

    suspend operator fun invoke(tierId: Long): TierDomainModel =
        tierRepository.getTierById(tierId)
}