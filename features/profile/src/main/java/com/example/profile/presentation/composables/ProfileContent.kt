package com.example.profile.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.domain.model.PostDomainModel
import com.example.ui.model.UserDetailsUiModel
import kotlinx.coroutines.flow.Flow

@Composable
fun ProfileContent(
    userDetails: UserDetailsUiModel,
    subscribers: String,
    postsFlow: Flow<PagingData<PostDomainModel>>,
    onSubscribeClick: () -> Unit,
    onPostClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val posts = postsFlow.collectAsLazyPagingItems()

    Column(modifier = modifier) {
        ProfileHeader(
            name = userDetails.username,
            imageUrl = userDetails.imageUrl,
            subscribers = subscribers,
            joinedYear = userDetails.joinedYear,
            onSubscribeClick = onSubscribeClick
        )

        var selectedTabIndex by remember { mutableIntStateOf(0) }

        ProfileTabs(
            selectedTabIndex = selectedTabIndex,
            onTabSelected = { selectedTabIndex = it },
            modifier = Modifier.fillMaxWidth(),
        )

        when (selectedTabIndex) {
            0 -> PostsList(
                posts = posts,
                onPostClick = onPostClick,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 12.dp)
            )
            1 -> AboutScreen(text = userDetails.about, modifier = Modifier.padding(20.dp))
        }
    }
}