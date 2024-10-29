package com.example.savepost.usecase

import com.example.common.utils.Constants
import com.example.domain.model.PostDataDomainModel
import com.example.domain.model.ValidationResult
import com.example.domain.repository.PostRepository
import com.example.savepost.navigation.SavePostRouter
import com.example.ui.player.MediaPlayer
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SavePostInteractor @Inject constructor(
    private val savePostRouter: SavePostRouter,
    private val mediaPlayer: MediaPlayer,
    private val postRepository: PostRepository,
    private val coroutineDispatcher: CoroutineDispatcher
) {

    fun getPlayer() = mediaPlayer

    fun preparePlayer() {
        mediaPlayer.prepare()
    }

    fun playVideo(content: String) {
        mediaPlayer.play(content)
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
        mediaPlayer.release()
    }
}
