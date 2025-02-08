package com.example.forboost.features.profile.di

import com.example.forboost.features.profile.AdapterProfileRouter
import com.example.profile.api.domain.repository.SubscribeRepository
import com.example.profile.api.domain.repository.UserRepository
import com.example.profile.api.domain.usecase.GetCurrentUsernameUseCase
import com.example.profile.api.domain.usecase.IsCurrentUserUseCase
import com.example.profile.api.navigation.ProfileRouter
import com.example.profile.impl.data.repository.SubscribeRepositoryImpl
import com.example.profile.impl.data.repository.UserRepositoryImpl
import com.example.profile.impl.domain.usecase.GetCurrentUsernameUseCaseImpl
import com.example.profile.impl.domain.usecase.IsCurrentUserUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface FeatureProfileBinderModule {

    @Binds
    fun bindAdapterProfileRouterToProfileRouter(adapterProfileRouter: AdapterProfileRouter): ProfileRouter

    @Binds
    fun bindUserRepositoryImpl(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    fun bindSubscribeRepositoryImpl(subscribeRepositoryImpl: SubscribeRepositoryImpl): SubscribeRepository

    @Binds
    fun bindGetCurrentUsernameUseCase(getCurrentUsernameUseCaseImpl: GetCurrentUsernameUseCaseImpl): GetCurrentUsernameUseCase

    @Binds
    fun bindIsCurrentUserUseCase(isCurrentUserUseCaseImpl: IsCurrentUserUseCaseImpl): IsCurrentUserUseCase
}