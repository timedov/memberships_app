package com.example.forboost.di.features.profile.di

import com.example.forboost.di.features.profile.AdapterProfileRouter
import com.example.profile.navigation.ProfileRouter
import dagger.Binds
import dagger.Module

@Module
interface FeatureProfileBinderModule {

    @Binds
    fun bindAdapterProfileRouterToProfileRouter(adapterProfileRouter: AdapterProfileRouter): ProfileRouter
}