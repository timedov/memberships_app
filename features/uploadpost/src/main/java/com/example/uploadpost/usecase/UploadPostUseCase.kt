package com.example.uploadpost.usecase

import com.example.domain.model.PostDataDomainModel
import com.example.domain.repository.PostRepository
import com.example.domain.repository.UserRepository
import java.io.File
import javax.inject.Inject

class UploadPostUseCase @Inject constructor(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
) {

    suspend operator fun invoke(post: PostDataDomainModel, content: File?, mimeType: String?) {
        postRepository.savePost(
            post = post,
            content = content,
            mimeType = mimeType,
            username = userRepository.getCurrentUserCredentials()
        )
    }
}
