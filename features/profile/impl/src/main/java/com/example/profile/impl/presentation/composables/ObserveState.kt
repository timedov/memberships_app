package com.example.profile.impl.presentation.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.profile.impl.R
import com.example.profile.impl.presentation.model.ProfileState
import com.example.ui.model.PostUiModel
import com.example.ui.view.composable.ErrorScreen
import com.example.ui.view.composable.LoadingScreen

@Composable
internal fun ObserveState(
    state: ProfileState,
    posts: LazyPagingItems<PostUiModel>,
    onPostClick: (String) -> Unit,
    onSubscribeClick: () -> Unit,
    onRetryClick: () -> Unit
) {

   if (state.isError) {
       ErrorScreen(onRetryClick = onRetryClick)
   } else {
       ProfileContent(
           userDetails = state.userDetails,
           subscribers = state.subscribers,
           subscribeButtonTitle = stringResource(
               id = when {
                   state.isSubscribed -> R.string.you_are_subscribed
                   else -> R.string.subscribe
               }
           ),
           isCurrentUser = state.isCurrentUser,
           posts = posts,
           onSubscribeClick = onSubscribeClick,
           onPostClick = onPostClick,
           modifier = Modifier
               .fillMaxSize()
               .padding(horizontal = 4.dp)
       )
       
       LoadingScreen(isLoading = state.isLoading)
   }
}
