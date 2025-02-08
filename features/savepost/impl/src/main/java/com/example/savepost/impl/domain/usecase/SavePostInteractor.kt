package com.example.savepost.impl.domain.usecase

import com.example.feed.api.domain.model.PostDomainModel
import com.example.savepost.api.domain.model.ValidationResult
import com.example.feed.api.domain.repository.PostRepository
import com.example.savepost.api.navigation.SavePostRouter
import com.example.common.utils.Constants
import com.example.feed.api.domain.model.SavePostForm
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

internal class SavePostInteractor @Inject constructor(
    private val savePostRouter: SavePostRouter,
    private val postRepository: PostRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    suspend fun hasPostDraft(): Boolean =
        withContext(coroutineDispatcher) {
            postRepository.hasPostDraft()
        }

    suspend fun getPostDraft(): PostDomainModel =
        withContext(coroutineDispatcher) {
            postRepository.getPostDraft()
        }

    fun validateTitle(title: String): ValidationResult =
        if (title.length in Constants.MIN_POST_TITLE_LENGTH..Constants.MAX_POST_TITLE_LENGTH)
            ValidationResult(isValid = true)
        else ValidationResult(
            isValid = false,
            errorMessage =
            "Title must be between ${Constants.MIN_POST_TITLE_LENGTH} and " +
                    "${Constants.MAX_POST_TITLE_LENGTH} characters"
        )

    fun validateDescription(description: String): ValidationResult =
        if (description.length in 0..Constants.MAX_POST_DESCRIPTION_LENGTH)
            ValidationResult(isValid = true)
        else
            ValidationResult(
                isValid = false,
                errorMessage =
                "Description must be less than ${Constants.MAX_POST_DESCRIPTION_LENGTH} characters"
            )

    suspend fun savePostDraft(post: PostDomainModel) {
        withContext(coroutineDispatcher) {
            postRepository.savePostDraft(
                SavePostForm(
                    id = post.id,
                    title = post.title,
                    content = post.content,
                    postedAt = post.postedAt,
                    author = post.author,
                    body = post.body,
                    requiresSubscription = post.requiresSubscription
                )
            )
        }
    }

    suspend fun removePostDraft() {
        withContext(coroutineDispatcher) {
            postRepository.removePostDraft()
        }
    }

    fun navigateToProfile() {
        savePostRouter.navigateToProfile()
    }

    fun uploadPost() {
        savePostRouter.runUploadPost()
    }
}
