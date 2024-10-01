package com.example.network.mapper

import com.example.domain.model.TierDomainModel
import com.example.network.remote.datasource.requests.SaveTierRequest
import com.example.network.remote.datasource.responses.TierResponse
import javax.inject.Inject

class TierDomainModelMapper @Inject constructor() {

    fun mapResponseToDomainModel(response: TierResponse): TierDomainModel =
        if (isResponseEmpty(response)) {
            TierDomainModel(
                id = response.id,
                name = response.name,
                price = response.price,
                description = response.description
            )
        } else TierDomainModel()

    fun mapDomainModelToRequest(domainModel: TierDomainModel, author: String): SaveTierRequest =
        SaveTierRequest(
            name = domainModel.name,
            author = author,
            price = domainModel.price,
            description = domainModel.description
        )

    fun mapResponseListToDomainModelList(responseList: List<TierResponse>): List<TierDomainModel> =
        responseList.map { mapResponseToDomainModel(it) }

    private fun isResponseEmpty(response: TierResponse) =
        response.id == -1L
                && response.name.isEmpty()
                && response.price == -1.0
                && response.description.isEmpty()
}