package com.example.domain.di

import com.example.common.di.AppScope
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
class DomainModule {

    @AppScope
    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}