package com.example.postdetails.presentation.model

import androidx.paging.PagingData
import com.example.domain.model.CommentDomainModel
import com.example.ui.model.PostDataUiModel
import com.example.ui.model.PostStatsUiModel
import com.example.ui.model.UserDetailsUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

sealed interface PostDetailsState {

    data object Loading : PostDetailsState
    data class Content(
        val userDetails: UserDetailsUiModel = UserDetailsUiModel(),
        val post: PostDataUiModel = PostDataUiModel(),
        val postStats: PostStatsUiModel = PostStatsUiModel(),
        val isFavorite: Boolean = false,
        val commentsResponse: Flow<PagingData<CommentDomainModel>> = emptyFlow(),
    ) : PostDetailsState
    data object Error : PostDetailsState
}
