package com.example.forboost.di.modules

import android.content.Context
import com.example.common.di.AppScope
import com.example.forboost.ForBoostApp
import dagger.Module
import dagger.Provides

@Module(includes = [])
class AppModule {
    @AppScope
    @Provides
    fun provideContext(application: ForBoostApp): Context {
        return application
    }
}