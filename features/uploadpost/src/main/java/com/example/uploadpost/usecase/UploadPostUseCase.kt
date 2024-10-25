package com.example.uploadpost.usecase

import com.example.domain.repository.PostRepository
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UploadPostUseCase @Inject constructor(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke() {
        withContext(coroutineDispatcher) {
            postRepository.savePost(
                post = postRepository.getPostDraft(),
                username = userRepository.getCurrentUserCredentials()
            )
        }
    }
}
