package com.example.postdetails.impl.presentation.model

import androidx.paging.PagingData
import com.example.postdetails.api.domain.model.CommentDomainModel
import com.example.ui.model.CommentUiModel
import com.example.ui.model.PostStatsUiModel
import com.example.ui.model.PostUiModel
import com.example.ui.model.UserUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

internal data class PostDetailsState(
    val postId: String = "",
    val userDetails: UserUiModel = UserUiModel(),
    val post: PostUiModel = PostUiModel(),
    val postStats: PostStatsUiModel = PostStatsUiModel(),
    val commentsFlow: Flow<PagingData<CommentUiModel>> = emptyFlow(),
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val requiresSubscription: Boolean = false,
    val isFavorite: Boolean = false,
    val commentValue: String = "",
    val isError: Boolean = false
)
