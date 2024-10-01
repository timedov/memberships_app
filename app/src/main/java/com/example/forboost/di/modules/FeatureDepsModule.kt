package com.example.forboost.di.modules

import com.example.auth.di.AuthDeps
import com.example.common.di.ComponentDeps
import com.example.common.di.ComponentDepsKey
import com.example.feed.di.FeedDeps
import com.example.forboost.di.components.AppComponent
import com.example.forboost.di.dependencies.DepsMap
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.Multibinds

@Module
interface FeatureDepsModule {

    @Multibinds
    fun depsMap(): DepsMap

    @Binds
    @[IntoMap ComponentDepsKey(AuthDeps::class)]
    fun authComponentDependencies(appComponent: AppComponent): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(FeedDeps::class)]
    fun feedComponentDependencies(appComponent: AppComponent): ComponentDeps
}