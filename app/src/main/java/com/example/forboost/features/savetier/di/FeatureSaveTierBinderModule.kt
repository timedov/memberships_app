package com.example.forboost.features.savetier.di

import com.example.forboost.features.savetier.AdapterSaveTierRouter
import com.example.savetier.navigation.SaveTierRouter
import dagger.Binds
import dagger.Module

@Module
interface FeatureSaveTierBinderModule {

    @Binds
    fun bindAdapterSaveTierRouterToSaveTierRouter(adapterSaveTierRouter: AdapterSaveTierRouter): SaveTierRouter
}