package com.example.uploadpost.api.domain.usecase

import com.example.feed.api.domain.model.PostDomainModel

interface UploadPostUseCase {

    suspend operator fun invoke(post: PostDomainModel)
}