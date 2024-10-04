package com.example.postdetails.presentation.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.domain.model.CommentDomainModel
import com.example.ui.utils.toUiModel
import com.example.ui.view.composables.ErrorScreen
import kotlinx.coroutines.flow.Flow

@Composable
fun CommentsList(
    response: Flow<PagingData<CommentDomainModel>>,
    onProfileClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val comments = response.collectAsLazyPagingItems()

    LazyColumn(
        modifier = modifier
    ) {
        items(comments.itemCount) { index ->
            comments[index]?.let {
                CommentItem(comment = it.toUiModel(), onProfileClick = onProfileClick)
            }
        }
    }

    comments.apply {
        when (loadState.refresh) {
            is LoadState.Error -> ErrorScreen(onRetryClick = { comments.retry() })
            is LoadState.Loading ->
                CircularProgressIndicator(modifier = Modifier.fillMaxWidth().wrapContentWidth())
            is LoadState.NotLoading -> ErrorScreen(onRetryClick = { comments.retry() })
        }
    }
}
