package com.example.forboost.features.profile.di

import com.example.forboost.features.profile.AdapterProfileRouter
import com.example.profile.navigation.ProfileRouter
import dagger.Binds
import dagger.Module

@Module
interface FeatureProfileBinderModule {

    @Binds
    fun bindAdapterProfileRouterToProfileRouter(adapterProfileRouter: AdapterProfileRouter): ProfileRouter
}