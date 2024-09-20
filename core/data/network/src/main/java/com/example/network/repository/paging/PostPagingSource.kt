package com.example.network.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.model.PostModel
import com.example.domain.model.Tier
import com.example.network.mapper.PostModelMapper
import com.example.network.remote.datasource.PostApi
import javax.inject.Inject

class PostPagingSource @Inject constructor(
    private val postApi: PostApi,
    private val postModelMapper: PostModelMapper
) : PagingSource<Int, PostModel>() {

    var tier = Tier.ALL_TIERS

    var author: String? = null

    override fun getRefreshKey(state: PagingState<Int, PostModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostModel> {
        return try {
            val page = params.key ?: 0
            val responseList = postModelMapper.mapResponseListToModelList(
                if (!author.isNullOrEmpty()) {
                    postApi.getPostsByAuthor(page = page, size = params.loadSize, author = author!!)
                }
                else if (tier == Tier.ALL_TIERS) {
                    postApi.getPosts(page = page, size = params.loadSize)
                } else {
                    postApi.getPostsByTier(page = page, size = params.loadSize, tier = tier.code)
                }
            )
            val nextPageNumber = if (responseList.size < params.loadSize) null else page + 1
            val prevKeyNumber = if (page == 0) null else page - 1
            LoadResult.Page(
                data = responseList,
                prevKey = prevKeyNumber,
                nextKey = nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}