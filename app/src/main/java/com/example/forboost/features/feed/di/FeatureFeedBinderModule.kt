package com.example.forboost.features.feed.di

import com.example.feed.api.domain.repository.PostRepository
import com.example.feed.api.domain.usecase.GetPostsUseCase
import com.example.feed.api.navigation.FeedRouter
import com.example.forboost.features.feed.AdapterFeedRouter
import com.example.feed.impl.data.repository.PostRepositoryImpl
import com.example.feed.impl.domain.usecase.GetPostsUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface FeatureFeedBinderModule {

    @Binds
    fun bindAdapterFeedRouterToFeedRouter(adapterAuthRouter: AdapterFeedRouter): FeedRouter

    @Binds
    fun bindGetPostsUseCaseImpl(getPostsUseCaseImpl: GetPostsUseCaseImpl): GetPostsUseCase

    @Binds
    fun bindPostRepositoryImpl(postRepositoryImpl: PostRepositoryImpl): PostRepository
}