package com.example.postdetails.impl.presentation.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.postdetails.impl.R
import com.example.postdetails.impl.presentation.model.PostDetailsAction
import com.example.ui.view.composable.ShowToast

@Composable
internal fun ObserveActions(
    action: PostDetailsAction
) {

    when (action) {
        PostDetailsAction.Initiate -> {}
        is PostDetailsAction.CommentSent ->
            ShowToast(message = stringResource(R.string.comment_sent_successfully))
        is PostDetailsAction.CommentSendingFailed ->
            ShowToast(message = stringResource(R.string.comment_sending_failed))
        is PostDetailsAction.SetFavoriteFailed ->
            ShowToast(message = stringResource(R.string.favorite_setting_failed))
    }
}