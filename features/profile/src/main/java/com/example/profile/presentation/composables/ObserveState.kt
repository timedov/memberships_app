package com.example.profile.presentation.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.profile.R
import com.example.profile.presentation.model.ProfileState
import com.example.ui.view.composables.ErrorScreen

@Composable
fun ObserveState(
    state: ProfileState,
    onPostClick: (Long) -> Unit,
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
               id = if (state.isCurrentUser) R.string.edit_tiers else R.string.subscribe
           ),
           postsFlow = state.postsFlow,
           onSubscribeClick = onSubscribeClick,
           onPostClick = onPostClick,
           modifier = Modifier
               .fillMaxSize()
               .padding(horizontal = 4.dp)
       )
   }
}
