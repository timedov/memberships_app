package com.example.uploadpost.impl.domain.usecase

import com.example.feed.api.domain.model.PostDomainModel
import com.example.feed.api.domain.repository.PostRepository
import com.example.uploadpost.api.domain.usecase.GetPostDraftUseCase
import javax.inject.Inject

class GetPostDraftUseCaseImpl @Inject constructor(
    private val postRepository: PostRepository
) : GetPostDraftUseCase {

     override suspend operator fun invoke(): PostDomainModel =
         postRepository.getPostDraft()
}
