package com.example.postdetails.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.media3.common.Player
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.domain.model.CommentDomainModel
import com.example.domain.model.ContentType
import com.example.ui.model.PostDataUiModel
import com.example.ui.model.PostStatsUiModel
import com.example.ui.model.UserDetailsUiModel
import com.example.ui.themes.Shapes
import com.example.ui.view.composables.AsyncImageCaching
import com.example.ui.view.composables.CommentTextField
import com.example.ui.view.composables.VideoPlayer
import com.example.ui.view.composables.commentsList
import kotlinx.coroutines.flow.Flow

@Composable
fun PostDetailsContent(
    userDetails: UserDetailsUiModel,
    post: PostDataUiModel,
    postStats: PostStatsUiModel,
    requiresSubscription: Boolean,
    isFavorite: Boolean,
    player: Player,
    commentsFlow: Flow<PagingData<CommentDomainModel>>,
    commentValue: String,
    onSubscribeClick: () -> Unit,
    onSubscribeDismiss: () -> Unit,
    onCommentValueChange: (String) -> Unit,
    onFavoriteClick: () -> Unit,
    onProfileClick: (String) -> Unit,
    onReplyClick: (String) -> Unit,
    onSendComment: () -> Unit,
    modifier: Modifier = Modifier
) {
    val comments = commentsFlow.collectAsLazyPagingItems()

    if (requiresSubscription) {
        SubscriptionRequiredDialog(
            onSubscribeClick = onSubscribeClick,
            onDismiss = onSubscribeDismiss
        )
    }

    LazyColumn(modifier = modifier.blur(if (requiresSubscription) 12.dp else 0.dp)) {
        item {
            if (post.author.isNotEmpty()) {
                PostProfileHeader(
                    authorName = userDetails.username,
                    authorImageUrl = userDetails.imageUrl.orEmpty(),
                    postTime = post.postedAgo,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onProfileClick(userDetails.username) }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        item {
            Text(
                text = post.title,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        if (post.content.isNotEmpty()) {
            item {
                when (post.contentType) {
                    ContentType.VIDEO -> {
                        VideoPlayer(
                            player = player,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp)
                                .aspectRatio(16 / 9f)
                        )
                    }
                    ContentType.IMAGE -> {
                        AsyncImageCaching(
                            model = post.content,
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(Shapes.medium),
                        )
                    }
                    else -> Unit
                }
            }
        }

        item {
            Text(
                text = post.body,
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.height(12.dp))
        }

        item {
            PostToolbar(
                favoriteCount = postStats.favoriteCount,
                commentsCount = postStats.commentsCount,
                isFavorite = isFavorite,
                onFavoriteClick = onFavoriteClick,
                modifier = Modifier.fillMaxWidth()
            )
        }

        item {
            CommentTextField(
                value = commentValue,
                onValueChange = onCommentValueChange,
                onCommentSend = onSendComment,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
            )
        }

        commentsList(
            comments = comments,
            onProfileClick = onProfileClick,
            onReplyClick = onReplyClick
        )
    }
}
