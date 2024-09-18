package com.example.profile.presentation.model

import com.example.domain.model.PostModel
import com.example.domain.model.UserDetailsModel

sealed interface ProfileState {
    data object Loading : ProfileState
    data class UserDetails(val userDetails: UserDetailsModel) : ProfileState
    data class Posts(val posts: List<PostModel>) : ProfileState
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
