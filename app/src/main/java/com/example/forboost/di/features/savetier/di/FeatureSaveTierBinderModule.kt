package com.example.forboost.di.features.savetier.di

import com.example.forboost.di.features.savetier.AdapterSaveTierRouter
import com.example.savetier.navigation.SaveTierRouter
import dagger.Binds
import dagger.Module

@Module
interface FeatureSaveTierBinderModule {

    @Binds
    fun bindAdapterSaveTierRouterToSaveTierRouter(adapterSaveTierRouter: AdapterSaveTierRouter): SaveTierRouter
}