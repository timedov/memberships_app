package com.example.uploadpost.api.domain.usecase

import com.example.feed.api.domain.model.PostDomainModel

interface GetPostDraftUseCase {

    suspend operator fun invoke(): PostDomainModel
}