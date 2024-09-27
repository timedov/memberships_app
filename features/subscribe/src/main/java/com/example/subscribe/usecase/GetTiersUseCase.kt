package com.example.subscribe.usecase

import com.example.ui.model.TierUiModel
import com.example.domain.repository.TierRepository
import com.example.ui.utils.toUiModel
import javax.inject.Inject

class GetTiersUseCase @Inject constructor(
    private val tierRepository: TierRepository
) {

    suspend operator fun invoke(username: String): List<TierUiModel> =
        tierRepository.getTiersByUser(username).map { it.toUiModel() }
}