package com.example.forboost.di.modules

import android.content.Context
import com.example.common.di.AppScope
import com.example.domain.di.DomainModule
import com.example.firebase.di.FirebaseModule
import com.example.forboost.ForBoostApp
import com.example.forboost.navigation.di.NavigationModule
import com.example.network.di.NetworkModule
import dagger.Module
import dagger.Provides

@Module(includes = [
    DomainModule::class,
    FeatureDepsModule::class,
    FeaturesModule::class,
    NavigationModule::class,
    NetworkModule::class,
    FirebaseModule::class,
])
class AppModule {
    @AppScope
    @Provides
    fun provideContext(application: ForBoostApp): Context {
        return application
    }
}