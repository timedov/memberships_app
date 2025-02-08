package com.example.uploadpost.impl.domain.usecase

import com.example.feed.api.domain.model.PostDomainModel
import com.example.feed.api.domain.model.SavePostForm
import com.example.feed.api.domain.repository.PostRepository
import com.example.profile.api.domain.repository.UserRepository
import com.example.uploadpost.api.domain.usecase.UploadPostUseCase
import com.example.uploadpost.impl.utils.toBase64
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.util.UUID
import javax.inject.Inject

class UploadPostUseCaseImpl @Inject constructor(
    private val postRepository: PostRepository,
    private val userRepository: UserRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) : UploadPostUseCase {

    override suspend operator fun invoke(post: PostDomainModel) {
        withContext(coroutineDispatcher) {
            postRepository.savePost(
                post = SavePostForm(
                    id = UUID.randomUUID().toString(),
                    title = post.title,
                    content =  post.content,
                    author = userRepository.getCurrentUserCredentials(),
                    postedAt = System.currentTimeMillis(),
                    body = post.body,
                    requiresSubscription = post.requiresSubscription
                )
            )
        }
    }
}
