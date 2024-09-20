package com.example.profile.presentation.model

import androidx.paging.PagingData
import com.example.domain.model.PostModel
import com.example.domain.model.UserDetailsModel
import kotlinx.coroutines.flow.Flow

sealed interface ProfileState {
    data object Loading : ProfileState
    data class Content(
        val userDetails: UserDetailsModel,
        val subscribers: String = "",
        val posts: Flow<PagingData<PostModel>>,
    ) : ProfileState
    data class Error(val message: String) : ProfileState
}

sealed interface ProfileEvent {
    data object Refresh : ProfileEvent
    data object SubscribeClick : ProfileEvent
    data class PostClick(val postId: Long) : ProfileEvent
    data object BackClick: ProfileEvent
}

sealed interface ProfileAction {
    data class ShowMessage(val message: String) : ProfileAction
}
