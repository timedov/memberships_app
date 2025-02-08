package com.example.postdetails.api.domain.usecase

import androidx.paging.PagingData
import com.example.postdetails.api.domain.model.CommentDomainModel
import kotlinx.coroutines.flow.Flow

interface GetCommentRepliesUseCase {

    operator fun invoke(parentCommentId: String): Flow<PagingData<CommentDomainModel>>
}