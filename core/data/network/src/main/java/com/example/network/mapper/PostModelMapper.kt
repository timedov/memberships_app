package com.example.network.mapper

import com.example.domain.model.PostModel
import com.example.network.remote.datasource.responses.PostResponse
import javax.inject.Inject

class PostModelMapper @Inject constructor() {

    fun mapResponseToModel(postResponse: PostResponse) =
        if (isResponseEmpty(postResponse)) {
            null
        } else {
            PostModel(
                postResponse.id,
                postResponse.title ?: "",
                postResponse.image ?: "",
                postResponse.category ?: "",
                postResponse.videosCount,
                postResponse.postsCount
            )
    }

    fun mapResponseListToModelList(postResponseList: List<PostResponse>) =
        postResponseList.mapNotNull { mapResponseToModel(it) }

    private fun isResponseEmpty(postResponse: PostResponse) =
        postResponse.id == 0L
                && postResponse.title == ""
                && postResponse.image == ""
                && postResponse.category == ""
}