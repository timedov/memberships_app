package com.example.network.mapper

import com.example.domain.model.ContentType
import com.example.domain.model.PostDataDomainModel
import com.example.domain.model.PostDomainModel
import com.example.network.remote.datasource.responses.PostDataResponse
import com.example.network.remote.datasource.responses.PostResponse
import javax.inject.Inject

class PostDomainModelMapper @Inject constructor() {

    private fun mapResponseToDomainModel(postResponse: PostResponse) =
        PostDomainModel(
            id = postResponse.id,
            title = postResponse.title.orEmpty(),
            image = postResponse.image.orEmpty(),
            category = postResponse.category.orEmpty(),
            postedAt = postResponse.postedTimestamp,
            author = postResponse.authorName.orEmpty()
        )

    fun mapDataResponseToDataDomainModel(postDataResponse: PostDataResponse) =
        PostDataDomainModel(
            id = postDataResponse.id,
            title = postDataResponse.title.orEmpty(),
            content = postDataResponse.content.orEmpty(),
            contentType =
            ContentType.entries.find { it.code == postDataResponse.contentType }
                ?: ContentType.NONE,
            category = postDataResponse.category.orEmpty(),
            postedAt = postDataResponse.postedTimestamp,
            author = postDataResponse.authorName.orEmpty(),
            body = postDataResponse.body.orEmpty(),
            isPaid = postDataResponse.requiresSubscription
        )

    fun mapResponseListToDomainModelList(postResponseList: List<PostResponse>) =
        postResponseList.map { mapResponseToDomainModel(it) }
}