package com.example.uploadpost.impl.domain.usecase

import com.example.feed.api.domain.repository.PostRepository
import com.example.uploadpost.api.domain.usecase.RemovePostDraftUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemovePostDraftUseCaseImpl @Inject constructor(
    private val postRepository: PostRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) : RemovePostDraftUseCase {

    override suspend fun invoke() {
        withContext(coroutineDispatcher) {
            postRepository.removePostDraft()
        }
    }
}