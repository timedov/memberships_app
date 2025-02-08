package com.example.feed.api.domain.usecase

import androidx.paging.PagingData
import com.example.feed.api.domain.model.PostDomainModel
import kotlinx.coroutines.flow.Flow

interface GetPostsOfUserUseCase {

    suspend operator fun invoke(username: String): Flow<PagingData<PostDomainModel>>
}