package com.example.uploadpost.usecase

import com.example.domain.model.PostDataDomainModel
import com.example.domain.repository.PostRepository
import com.example.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

class SavePostUseCase @Inject constructor(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(post: PostDataDomainModel, content: File?, mimeType: String?) {
        withContext(coroutineDispatcher) {
            postRepository.savePost(
                post = post,
                username = userRepository.getCurrentUserCredentials(),
                content = content,
                mimeType = mimeType
            )
        }
    }
}
