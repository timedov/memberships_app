package com.example.profile.impl.presentation.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.feed.api.domain.model.PostDomainModel
import com.example.profile.impl.R
import com.example.ui.model.PostUiModel
import com.example.ui.model.UserUiModel
import com.example.ui.themes.OnSurfaceTextAlpha
import kotlinx.coroutines.flow.Flow

@Composable
fun ProfileContent(
    userDetails: UserUiModel,
    subscribers: String,
    subscribeButtonTitle: String,
    isCurrentUser: Boolean = false,
    posts: LazyPagingItems<PostUiModel>,
    onSubscribeClick: () -> Unit,
    onPostClick: (String) -> Unit,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Column(modifier = modifier) {
        ProfileHeader(
            name = userDetails.username,
            imageUrl = userDetails.imageUrl,
            subscribers = subscribers,
            joinedYear = userDetails.joinedYear,
            isCurrentUser = isCurrentUser,
            subscribeButtonTitle = subscribeButtonTitle,
            onSubscribeClick = onSubscribeClick,
            modifier = Modifier.padding(vertical = 4.dp)
        )

        ProfileTabs(
            selectedTabIndex = selectedTabIndex,
            onTabSelected = { selectedTabIndex = it },
            modifier = Modifier.fillMaxWidth(),
        )

        when (selectedTabIndex) {
            0 -> if (posts.itemCount > 0) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.surfaceContainer)
                        .padding(vertical = 8.dp)
                ) {
                    postsList(
                        posts = posts,
                        onPostClick = onPostClick
                    )
                }
            } else {
                Text(
                    text = stringResource(id = R.string.no_posts),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = OnSurfaceTextAlpha),
                    modifier = modifier.padding(16.dp)
                )
            }
            1 -> AboutScreen(
                text = userDetails.about,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}