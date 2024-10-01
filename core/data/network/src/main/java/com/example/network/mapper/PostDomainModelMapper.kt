package com.example.network.mapper

import com.example.domain.model.PostDomainModel
import com.example.network.remote.datasource.responses.PostResponse
import javax.inject.Inject

class PostDomainModelMapper @Inject constructor() {

    fun mapResponseToDomainModel(postResponse: PostResponse) =
        PostDomainModel(
            id = postResponse.id,
            title = postResponse.title.orEmpty(),
            image = postResponse.image.orEmpty(),
            category = postResponse.category.orEmpty(),
            postedAt = postResponse.postedTimestamp,
            author = postResponse.authorName.orEmpty()
        )

    fun mapResponseListToDomainModelList(postResponseList: List<PostResponse>) =
        postResponseList.map { mapResponseToDomainModel(it) }
}