package com.example.forboost.features.postdetails.di

import com.example.forboost.features.postdetails.AdapterPostDetailsRouter
import com.example.postdetails.navigation.PostDetailsRouter
import dagger.Binds
import dagger.Module

@Module
interface FeaturePostDetailsBinderModule {

    @Binds
    fun bindAdapterPostDetailsRouterToPostDetailsRouter(adapter: AdapterPostDetailsRouter): PostDetailsRouter
}