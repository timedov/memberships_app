package com.example.forboost.di.modules

import com.example.forboost.di.dependencies.DepsMap
import dagger.Module
import dagger.multibindings.Multibinds

@Module
interface FeatureDepsModule {

    @Multibinds
    fun depsMap(): DepsMap
}