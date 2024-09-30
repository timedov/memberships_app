package com.example.forboost.di.modules

import com.example.common.di.ComponentDeps
import com.example.common.di.ComponentDepsKey
import com.example.forboost.di.components.AppComponent
import com.example.forboost.di.dependencies.DepsMap
import com.example.savetier.di.SaveTierDeps
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.Multibinds

@Module
interface FeatureDepsModule {

    @Multibinds
    fun depsMap(): DepsMap

    @[Binds IntoMap ComponentDepsKey(SaveTierDeps::class)]
    fun bindsSaveTierDeps(appComponent: AppComponent): ComponentDeps
}