package com.example.commentreplies.presentation.model

import androidx.paging.PagingData
import com.example.domain.model.CommentDomainModel
import com.example.ui.model.CommentUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class CommentRepliesState(

    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val parentCommentId: String = "",
    val parentComment: CommentUiModel = CommentUiModel(),
    val commentsFlow: Flow<PagingData<CommentDomainModel>> = emptyFlow(),
    val commentValue: String = "",
    val isError: Boolean = false
)
