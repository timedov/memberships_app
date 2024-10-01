package com.example.forboost.features.auth.di

import com.example.auth.navigation.AuthRouter
import com.example.forboost.features.auth.AdapterAuthRouter
import dagger.Binds
import dagger.Module

@Module
interface FeatureAuthBinderModule {

    @Binds
    fun bindAdapterAuthRouterToAuthRouter(adapterAuthRouter: AdapterAuthRouter): AuthRouter
}