package com.example.profile.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.domain.model.PostModel
import com.example.domain.model.UserDetailsModel
import com.example.profile.R
import com.example.ui.R as UiR
import com.example.profile.presentation.ProfileViewModel
import com.example.profile.presentation.model.ProfileEvent
import com.example.profile.presentation.model.ProfileState
import com.example.ui.themes.Shapes
import com.example.ui.view.composables.CenterAlignedTopAppBarWithBackButton
import com.example.ui.view.composables.ErrorScreen
import com.example.ui.view.composables.LoadingScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(viewModel: ProfileViewModel) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBarWithBackButton(
                title = stringResource(id = R.string.profile),
                onBackClick = { viewModel.obtainEvent(ProfileEvent.BackClick) }
            )
        }
    ) { paddingValues ->
        PullToRefreshBox(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            isRefreshing = false,
            onRefresh = { viewModel.obtainEvent(ProfileEvent.Refresh) }
        ) {
            ObserveState(state, viewModel)
        }
    }
}

@Composable
private fun ObserveState(
    state: ProfileState,
    viewModel: ProfileViewModel
) {
    val userDetails = remember { mutableStateOf(UserDetailsModel())}
    val posts = remember { mutableStateOf<List<PostModel>>(emptyList()) }

    when (state) {
        is ProfileState.Loading -> LoadingScreen(isLoading = true)
        is ProfileState.UserDetails -> userDetails.value = state.userDetails
        is ProfileState.Posts -> posts.value = state.posts
        is ProfileState.Error -> ErrorScreen { viewModel.obtainEvent(ProfileEvent.Refresh) }
    }

    ProfileContent(
        userDetails = userDetails.value,
        posts = posts.value,
        onSubscribeClick = { viewModel.obtainEvent(ProfileEvent.SubscribeClick) },
        onPostClick = { viewModel.obtainEvent(ProfileEvent.PostClick(it)) },
    )
}

@Composable
fun ProfileContent(
    userDetails: UserDetailsModel,
    posts: List<PostModel>,
    onSubscribeClick: () -> Unit,
    onPostClick: (Long) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        ProfileHeader(
            name = userDetails.username,
            imageUrl = userDetails.imageUrl,
            subscribers = userDetails.subscribers,
            joinedYear = userDetails.joinedYear,
            onSubscribeClick = onSubscribeClick
        )

        var selectedTabIndex by remember { mutableIntStateOf(0) }

        ProfileTabs(selectedTabIndex = selectedTabIndex, onTabSelected = { selectedTabIndex = it })

        when (selectedTabIndex) {
            0 -> {} //PostsList(posts = posts, onPostClick = onPostClick)
            1 -> AboutScreen(text = userDetails.about)
        }
    }
}

@Composable
fun ProfileHeader(
    name: String,
    imageUrl: String?,
    subscribers: String,
    joinedYear: Int,
    onSubscribeClick: () -> Unit
) {
    AsyncImage(
        model = imageUrl ?: "",
        placeholder = painterResource(id = UiR.drawable.empty_profile_pic),
        error = painterResource(id = UiR.drawable.empty_profile_pic),
        contentDescription = stringResource(R.string.profile_image),
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .size(130.dp)
            .clip(CircleShape)
    )
    Text(
        text = name,
        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(horizontal = 20.dp)
    )
    if (subscribers != "") {
        Text(
            text = stringResource(R.string.count_subscribers, subscribers),
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
            modifier = Modifier.padding(vertical = 4.dp, horizontal = 20.dp)
        )
    }
    Text(
        text = stringResource(R.string.joined_in_year, joinedYear),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 20.dp)
    )
    Button(
        onClick = onSubscribeClick,
        colors = ButtonDefaults.filledTonalButtonColors(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        shape = Shapes.large,
    ) {
        Text(
            text = stringResource(R.string.subscribe),
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}

@Composable
fun ProfileTabs(
    selectedTabIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    val tabs = listOf(
        stringResource(R.string.posts),
        stringResource(R.string.about)
    )

    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                selected = selectedTabIndex == index,
                onClick = { onTabSelected(index) },
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                text = {
                    Text(
                        text = title,
                        style = if (selectedTabIndex == index) {
                            MaterialTheme.typography.bodyLarge
                        } else {
                            MaterialTheme.typography.bodyMedium
                        }
                    )
                }
            )
        }
    }
}

@Composable
fun AboutScreen(text: String) {
    Text(
        text = text.ifEmpty { "No description" },
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(20.dp)
    )
}

