package com.example.network.mapper

import com.example.common.utils.timeAgo
import com.example.domain.model.PostModel
import com.example.network.remote.datasource.responses.PostResponse
import javax.inject.Inject

class PostModelMapper @Inject constructor() {

    fun mapResponseToModel(postResponse: PostResponse): PostModel? =
        if (isResponseEmpty(postResponse).not()) {
            PostModel(
                id = postResponse.id,
                title = postResponse.title!!,
                image = postResponse.image!!,
                category = postResponse.category!!,
                postedAgo = postResponse.postedTimestamp.timeAgo(),
                author = postResponse.authorName!!
            )
        } else null

    fun mapResponseListToModelList(postResponseList: List<PostResponse>) =
        postResponseList.mapNotNull { mapResponseToModel(it) }

    private fun isResponseEmpty(postResponse: PostResponse) =
        postResponse.id == 0L
                && postResponse.title.isNullOrEmpty()
                && postResponse.image.isNullOrEmpty()
                && postResponse.category.isNullOrEmpty()
                && postResponse.authorName.isNullOrEmpty()

}