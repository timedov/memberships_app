package com.example.profile.presentation.model

import androidx.paging.PagingData
import com.example.domain.model.PostDomainModel
import com.example.domain.model.UserDetailsDomainModel
import kotlinx.coroutines.flow.Flow

sealed interface ProfileState {
    data object Loading : ProfileState
    data class Content(
        val userDetails: UserDetailsDomainModel,
        val subscribers: String = "",
        val posts: Flow<PagingData<PostDomainModel>>,
    ) : ProfileState
    data class Error(val message: String) : ProfileState
}
