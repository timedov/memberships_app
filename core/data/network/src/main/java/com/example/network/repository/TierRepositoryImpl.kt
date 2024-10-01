package com.example.network.repository

import com.example.domain.model.TierDomainModel
import com.example.domain.repository.TierRepository
import com.example.network.mapper.TierDomainModelMapper
import com.example.network.remote.datasource.TierApi
import javax.inject.Inject

class TierRepositoryImpl @Inject constructor(
    private val tierApi: TierApi,
    private val tierDomainModelMapper: TierDomainModelMapper
) : TierRepository {

    override suspend fun getTiersByUser(username: String): List<TierDomainModel> =
        tierDomainModelMapper.mapResponseListToDomainModelList(
            tierApi.getTiersByUser(username = username)
        )

    override suspend fun getTierById(id: Long): TierDomainModel =
        tierDomainModelMapper.mapResponseToDomainModel(tierApi.getTierById(id = id))

    override suspend fun saveTier(tier: TierDomainModel, username: String) {
        tierApi.saveTier(
            tierDomainModelMapper.mapDomainModelToRequest(domainModel = tier, author = username),
        )
    }
}