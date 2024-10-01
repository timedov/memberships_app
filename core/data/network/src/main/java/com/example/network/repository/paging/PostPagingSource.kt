package com.example.network.repository.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.model.PostDomainModel
import com.example.domain.model.TierType
import com.example.network.mapper.PostDomainModelMapper
import com.example.network.remote.datasource.PostApi
import com.example.network.remote.datasource.responses.isResponseEmpty
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

class PostPagingSource @Inject constructor(
    private val postApi: PostApi,
    private val postModelMapper: PostDomainModelMapper
) : PagingSource<Int, PostDomainModel>() {

    var tier = TierType.ALL_TIERS

    var author: String = ""

    override fun getRefreshKey(state: PagingState<Int, PostDomainModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PostDomainModel> {
        return try {
            val page = params.key ?: 0
            val responseList = postModelMapper.mapResponseListToDomainModelList(
                if (author.isNotEmpty())
                    postApi.getPostsByAuthor(
                        author = author,
                        size = params.loadSize,
                        page = page
                    ).filter { it.isResponseEmpty().not() }
                 else postApi.getPostsByTier(
                    tier = tier.code,
                    size = params.loadSize,
                    page = page,
                ).filter { it.isResponseEmpty().not() }
            )
            val nextPageNumber = if (responseList.size < params.loadSize) null else page + 1
            val prevKeyNumber = if (page == 0) null else page - 1
            LoadResult.Page(
                data = responseList,
                prevKey = prevKeyNumber,
                nextKey = nextPageNumber
            )
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: UnknownHostException) {
            return LoadResult.Error(e)
        }
    }
}
