package com.example.profile.usecase

import androidx.paging.PagingData
import com.example.domain.model.PostDomainModel
import com.example.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPostsOfUserUseCase @Inject constructor(
    private val postRepository: PostRepository,
) {

    operator fun invoke(username: String): Flow<PagingData<PostDomainModel>> =
        postRepository.getPostsOfUser(username)
}
