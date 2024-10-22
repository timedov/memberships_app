package com.example.savepost.usecase

import com.example.domain.model.PostDataDomainModel
import com.example.domain.repository.PostRepository
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SavePostUseCase @Inject constructor(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(post: PostDataDomainModel) {
        withContext(coroutineDispatcher) {
            postRepository.savePost(
                post = post,
                username = userRepository.getCurrentUserCredentials()
            )
        }
    }
}
