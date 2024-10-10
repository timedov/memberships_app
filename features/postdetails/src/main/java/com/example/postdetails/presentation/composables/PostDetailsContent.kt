package com.example.postdetails.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
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
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.domain.model.CommentDomainModel
import com.example.ui.model.PostDataUiModel
import com.example.ui.model.PostStatsUiModel
import com.example.ui.model.UserDetailsUiModel
import com.example.ui.themes.Shapes
import com.example.ui.view.composables.AsyncImageCaching
import kotlinx.coroutines.flow.Flow

@Composable
fun PostDetailsContent(
    userDetails: UserDetailsUiModel,
    post: PostDataUiModel,
    postStats: PostStatsUiModel,
    isFavorite: Boolean,
    commentsFlow: Flow<PagingData<CommentDomainModel>>,
    commentValue: String,
    onCommentValueChange: (String) -> Unit,
    onFavoriteClick: () -> Unit,
    onProfileClick: (String) -> Unit,
    onSendComment: () -> Unit,
    modifier: Modifier = Modifier
) {
    val comments = commentsFlow.collectAsLazyPagingItems()

    LazyColumn(modifier = modifier) {
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
                if (post.isVideo) {
                    TODO("Add video player")
                } else {
                    AsyncImageCaching(
                        model = post.content,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(Shapes.medium)
                            .blur(if (post.isPaid) 8.dp else 0.dp),
                    )
                }
            }
        }

        item {
            Text(
                text = post.body,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.blur(if (post.isPaid) 8.dp else 0.dp)
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

        commentsList(comments = comments, onProfileClick = onProfileClick)
    }
}
