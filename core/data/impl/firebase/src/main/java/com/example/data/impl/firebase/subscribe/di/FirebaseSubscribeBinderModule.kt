package com.example.data.impl.firebase.subscribe.di

import com.example.data.api.subscribe.datasource.SubscribeDataSource
import com.example.data.impl.firebase.subscribe.datasource.FirebaseSubscribeDataSource
import dagger.Binds
import dagger.Module

@Module
internal interface FirebaseSubscribeBinderModule {

    @Binds
    fun bindSubscribeDataSource(impl: FirebaseSubscribeDataSource): SubscribeDataSource
}