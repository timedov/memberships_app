package com.example.postdetails.presentation.model

import androidx.paging.PagingData
import com.example.domain.model.CommentDomainModel
import com.example.ui.model.PostDataUiModel
import com.example.ui.model.PostStatsUiModel
import com.example.ui.model.UserDetailsUiModel
import com.example.ui.player.MediaPlayer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class PostDetailsState(
    val postId: Long = -1L,
    val authorName: String = "",
    val userDetails: UserDetailsUiModel = UserDetailsUiModel(),
    val post: PostDataUiModel = PostDataUiModel(),
    val postStats: PostStatsUiModel = PostStatsUiModel(),
    val commentsFlow: Flow<PagingData<CommentDomainModel>> = emptyFlow(),
    val isLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val requiresSubscription: Boolean = false,
    val player: MediaPlayer,
    val isFavorite: Boolean = false,
    val commentValue: String = "",
    val isError: Boolean = false
)
