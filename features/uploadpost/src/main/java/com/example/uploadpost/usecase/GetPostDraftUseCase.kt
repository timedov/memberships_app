package com.example.uploadpost.usecase

import com.example.domain.model.PostDataDomainModel
import com.example.domain.repository.PostRepository
import javax.inject.Inject

class GetPostDraftUseCase @Inject constructor(
    private val postRepository: PostRepository
) {

    suspend operator fun invoke(): PostDataDomainModel = postRepository.getPostDraft()
}
