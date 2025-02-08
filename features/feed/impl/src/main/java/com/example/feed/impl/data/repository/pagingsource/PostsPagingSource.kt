package com.example.feed.impl.data.repository.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.data.api.post.datasource.PostDataSource
import com.example.data.api.post.model.PostDataModel

internal class PostsPagingSource(
    private val postDataSource: PostDataSource,
) : PagingSource<Pair<Long, String>, PostDataModel>() {

    override suspend fun load(
        params: LoadParams<Pair<Long, String>>
    ): LoadResult<Pair<Long, String>, PostDataModel> {
        return try {
            val nextKey = params.key ?: Pair(Long.MAX_VALUE, "")
            val posts = postDataSource.getPosts(params.loadSize, nextKey.first, nextKey.second)

            val newNextKey = posts.lastOrNull()?.let { Pair(it.postedAt, it.id) }

            LoadResult.Page(
                data = posts,
                prevKey = null,
                nextKey = newNextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(
        state: PagingState<Pair<Long, String>, PostDataModel>
    ): Pair<Long, String>? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.nextKey
        }
    }
}
