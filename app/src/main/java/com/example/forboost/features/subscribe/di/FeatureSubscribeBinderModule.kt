package com.example.forboost.features.subscribe.di

import com.example.forboost.features.subscribe.AdapterSubscribeRouter
import com.example.subscribe.navigation.SubscribeRouter
import dagger.Binds
import dagger.Module

@Module
interface FeatureSubscribeBinderModule {

    @Binds
    fun bindAdapterSubscribeRouterToSubscribeRouter(adapterSubscribeRouter: AdapterSubscribeRouter): SubscribeRouter
}