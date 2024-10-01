package com.example.savetier.usecase

import com.example.domain.model.TierDomainModel
import com.example.domain.repository.TierRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetTierUseCase @Inject constructor(
    private val tierRepository: TierRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(tierId: Long): TierDomainModel =
        withContext(coroutineDispatcher) {
            tierRepository.getTierById(tierId)
        }
}