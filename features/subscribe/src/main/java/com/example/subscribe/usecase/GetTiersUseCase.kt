package com.example.subscribe.usecase

import com.example.ui.model.TierUiModel
import com.example.domain.repository.TierRepository
import com.example.ui.utils.toUiModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetTiersUseCase @Inject constructor(
    private val tierRepository: TierRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(username: String): List<TierUiModel> =
        withContext(coroutineDispatcher) {
            tierRepository.getTiersByUser(username).map { it.toUiModel() }
        }
}