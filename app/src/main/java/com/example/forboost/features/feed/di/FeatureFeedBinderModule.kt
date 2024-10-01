package com.example.forboost.features.feed.di

import com.example.feed.navigation.FeedRouter
import com.example.forboost.features.feed.AdapterFeedRouter
import dagger.Binds
import dagger.Module

@Module
interface FeatureFeedBinderModule {
    @Binds
    fun bindAdapterFeedRouterToFeedRouter(adapterAuthRouter: AdapterFeedRouter): FeedRouter
}