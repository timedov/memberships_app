package com.example.postdetails.presentation

import androidx.lifecycle.viewModelScope
import com.example.common.utils.ExceptionHandlerDelegate
import com.example.common.utils.runSuspendCatching
import com.example.postdetails.navigation.PostDetailsRouter
import com.example.postdetails.presentation.model.PostDetailsAction
import com.example.postdetails.presentation.model.PostDetailsEvent
import com.example.postdetails.presentation.model.PostDetailsState
import com.example.ui.base.BaseViewModel
import com.example.ui.model.PostDataUiModel
import com.example.ui.model.PostStatsUiModel
import com.example.ui.model.UserDetailsUiModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class PostDetailsViewModel @Inject constructor(
    private val postDetailsRouter: PostDetailsRouter,
    private val exceptionHandlerDelegate: ExceptionHandlerDelegate
) : BaseViewModel<PostDetailsState, PostDetailsEvent, PostDetailsAction>(
    initialState = PostDetailsState.Loading
) {

    var postId: Long = -1L
    var authorName: String = ""

    init {
        loadPostData()
    }

    override fun obtainEvent(event: PostDetailsEvent) {
        when (event) {
            is PostDetailsEvent.BackClick -> popBackStack()
            is PostDetailsEvent.FavoriteClick -> onFavoriteClicked()
            is PostDetailsEvent.ProfileClick -> navigateToProfileScreen()
            is PostDetailsEvent.SendComment -> sendComment(event.text)
            is PostDetailsEvent.Refresh -> loadPostData()
        }
    }

    private fun loadPostData() {

        _uiState.value = PostDetailsState.Loading

        runSuspendCatching(exceptionHandlerDelegate) {

        }.onSuccess {
            _uiState.value = PostDetailsState.Content(
                post = PostDataUiModel(
                    title = "Bebalaboba: the greatest app in the world",
                    content = "риаптап",
                    isVideo = false,
                    category = "Android",
                    postedAgo = "2 days ago",
                    author = "Bebalaboba",
                    body = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam eu mattis nisl. Morbi id nulla non augue varius posuere. Ut hendrerit ante sem, in feugiat nibh accumsan id. Suspendisse tempor, felis eget mollis porta, arcu libero varius ante, sit amet pretium lacus leo quis arcu. Integer lobortis quam.",
                    isPaid = false
                ),
            )

            loadAuthorDetails()
        }.onFailure {
            _uiState.value = PostDetailsState.Error
        }
    }

    private fun loadAuthorDetails() {
        runSuspendCatching(exceptionHandlerDelegate) {

        }.onSuccess {
            _uiState.value = (_uiState.value as PostDetailsState.Content).copy(
                userDetails = UserDetailsUiModel(
                    username = "Bebalaboba",
                    imageUrl = "",
                )
            )

            loadPostStats()
        }.onFailure {
            _uiState.value = PostDetailsState.Error
        }
    }

    private fun loadPostStats() {
        runSuspendCatching(exceptionHandlerDelegate) {

        }.onSuccess {
            _uiState.value = (_uiState.value as PostDetailsState.Content).copy(
                postStats = PostStatsUiModel(
                    favoriteCount = 45.toString(),
                    commentsCount = 2.toString()
                )
            )

            loadComments()
        }.onFailure {
            _uiState.value = PostDetailsState.Error
        }
    }

    private fun loadComments() {
        viewModelScope.launch {
            runSuspendCatching(exceptionHandlerDelegate) {

            }.onSuccess {

            }
        }
    }

    private fun popBackStack() {
        postDetailsRouter.popBackStack()
    }

    private fun onFavoriteClicked() {
        TODO("Not yet implemented")
    }

    private fun navigateToProfileScreen() {
        postDetailsRouter.navigateToProfile(username = authorName)
    }

    private fun sendComment(text: String) {
        TODO("Not yet implemented")
    }
}
