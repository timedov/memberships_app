package com.example.forboost.di.modules

import android.content.Context
import com.example.common.di.AppScope
import com.example.forboost.ForBoostApp
import com.example.forboost.navigation.di.NavigationModule
import dagger.Module
import dagger.Provides

@Module(includes = [
    FeatureDepsModule::class,
    FeaturesModule::class,
    NavigationModule::class
])
class AppModule {
    @AppScope
    @Provides
    fun provideContext(application: ForBoostApp): Context {
        return application
    }
}