package com.example.forboost.di.modules

import com.example.auth.di.AuthDeps
import com.example.common.di.ComponentDeps
import com.example.common.di.ComponentDepsKey
import com.example.forboost.di.components.AppComponent
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FeatureDepsModule {

    @Binds
    @[IntoMap ComponentDepsKey(AuthDeps::class)]
    fun authComponentDependencies(appComponent: AppComponent): ComponentDeps
}