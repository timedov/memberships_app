package com.example.postdetails.impl.data.repository.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.api.comment.datasource.CommentDataSource
import com.example.data.api.comment.model.CommentDataModel

internal class CommentPagingSource(
    private val postId: String,
    private val commentDataSource: CommentDataSource,
) : PagingSource<Pair<Long, String>, CommentDataModel>() {

    override suspend fun load(
        params: LoadParams<Pair<Long, String>>
    ): LoadResult<Pair<Long, String>, CommentDataModel> {
        return try {
            val nextKey = params.key ?: Pair(Long.MIN_VALUE, "")
            val comments = commentDataSource.getComments(
                postId = postId,
                limit = params.loadSize,
                startAfter = if (nextKey.first == Long.MIN_VALUE) null else nextKey.first,
                startAfterId = nextKey.second.ifEmpty { null }
            )

            val newNextKey = comments.lastOrNull()?.let { Pair(it.postedAt, it.id) }

            LoadResult.Page(
                data = comments,
                prevKey = null,
                nextKey = newNextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Pair<Long, String>, CommentDataModel>): Pair<Long, String>? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.nextKey
        }
    }
}
