package com.example.forboost.di.features.subscribe.di

import com.example.forboost.di.features.subscribe.AdapterSubscribeRouter
import com.example.subscribe.navigation.SubscribeRouter
import dagger.Binds
import dagger.Module

@Module
interface FeatureSubscribeBinderModule {

    @Binds
    fun bindAdapterSubscribeRouterToSubscribeRouter(adapterSubscribeRouter: AdapterSubscribeRouter): SubscribeRouter
}