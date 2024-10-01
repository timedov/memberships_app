package com.example.network.mapper

import com.example.domain.model.PostDomainModel
import com.example.network.remote.datasource.responses.PostResponse
import javax.inject.Inject

class PostDomainModelMapper @Inject constructor() {

    fun mapResponseToModel(postResponse: PostResponse) =
        if (isResponseEmpty(postResponse)) {
            null
        } else {
            PostDomainModel(
                id = postResponse.id,
                title = postResponse.title.orEmpty(),
                image = postResponse.image.orEmpty(),
                category = postResponse.category.orEmpty(),
                postedAt = postResponse.postedTimestamp,
                author = postResponse.authorName.orEmpty()
            )
    }

    fun mapResponseListToModelList(postResponseList: List<PostResponse>) =
        postResponseList.mapNotNull { mapResponseToModel(it) }

    private fun isResponseEmpty(postResponse: PostResponse) =
        postResponse.id == 0L
                && postResponse.title.isNullOrEmpty()
                && postResponse.image.isNullOrEmpty()
                && postResponse.category.isNullOrEmpty()
}