package com.example.savepost.usecase

import androidx.media3.common.Player
import com.example.common.utils.Constants
import com.example.domain.model.PostDataDomainModel
import com.example.domain.model.ValidationResult
import com.example.domain.repository.PostRepository
import com.example.savepost.navigation.SavePostRouter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SavePostInteractor @Inject constructor(
    private val savePostRouter: SavePostRouter,
    private val player: Player,
    private val postRepository: PostRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    fun getPlayer() = player

    fun preparePlayer() {
        player.prepare()
    }

    suspend fun getPostDraft(): PostDataDomainModel =
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

    suspend fun savePostDraft(post: PostDataDomainModel) {
        withContext(coroutineDispatcher) {
            postRepository.savePostDraft(post)
        }
    }

    fun popBackStack() {
        savePostRouter.popBackStack()
    }

    fun releasePlayer() {
        player.release()
    }
}
