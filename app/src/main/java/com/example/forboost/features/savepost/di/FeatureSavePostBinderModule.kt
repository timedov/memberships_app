package com.example.forboost.features.savepost.di

import com.example.forboost.features.savepost.AdapterSavePostRouter
import com.example.savepost.navigation.SavePostRouter
import dagger.Binds
import dagger.Module

@Module
interface FeatureSavePostBinderModule {

    @Binds
    fun bindAdapterSavePostRouterToSavePostRouter(adapterSavePostRouter: AdapterSavePostRouter): SavePostRouter
}
