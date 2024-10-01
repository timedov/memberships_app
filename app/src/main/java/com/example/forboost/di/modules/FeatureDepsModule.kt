package com.example.forboost.di.modules

import com.example.auth.di.AuthDeps
import com.example.common.di.ComponentDeps
import com.example.common.di.ComponentDepsKey
import com.example.feed.di.FeedDeps
import com.example.forboost.di.components.AppComponent
import com.example.forboost.di.dependencies.DepsMap
import com.example.profile.di.ProfileDeps
import com.example.subscribe.di.SubscribeDeps
import com.example.savetier.di.SaveTierDeps
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
    fun bindAuthDeps(appComponent: AppComponent): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(FeedDeps::class)]
    fun bindFeedDeps(appComponent: AppComponent): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(ProfileDeps::class)]
    fun bindProfileDeps(appComponent: AppComponent): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(SubscribeDeps::class)]
    fun bindSubscribeDeps(appComponent: AppComponent): ComponentDeps

    @[Binds IntoMap ComponentDepsKey(SaveTierDeps::class)]
    fun bindsSaveTierDeps(appComponent: AppComponent): ComponentDeps
}