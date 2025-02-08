package com.example.commentreplies.impl.presentation.model

import androidx.paging.PagingData
import com.example.postdetails.api.domain.model.CommentDomainModel
import com.example.ui.model.CommentUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

internal data class CommentRepliesState(

    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val parentCommentId: String = "",
    val parentComment: CommentUiModel = CommentUiModel(),
    val commentsFlow: Flow<PagingData<CommentUiModel>> = emptyFlow(),
    val commentValue: String = "",
    val isError: Boolean = false
)
