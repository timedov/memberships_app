package com.example.forboost.di.modules

import android.content.Context
import com.example.common.di.AppScope
import com.example.data.impl.firebase.di.FirebaseModule
import com.example.forboost.ForBoostApp
import com.example.forboost.navigation.di.NavigationModule
import com.example.local.di.RoomModule
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module(includes = [
    FeatureDepsModule::class,
    FeaturesModule::class,
    NavigationModule::class,
    RoomModule::class,
    FirebaseModule::class,
])
class AppModule {
    @AppScope
    @Provides
    fun provideContext(application: ForBoostApp): Context = application


    @AppScope
    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}