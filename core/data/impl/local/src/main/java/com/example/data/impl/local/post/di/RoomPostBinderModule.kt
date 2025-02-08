package com.example.local.post.di

import com.example.data.api.post.datasource.PostDraftLocalDataSource
import com.example.data.impl.local.post.api.RoomPostDraftLocalDataSource
import dagger.Binds
import dagger.Module

@Module
interface RoomPostBinderModule {

    @Binds
    fun bindPostDraftLocalDataSourceImpl(roomPostDraftLocalDataSource: RoomPostDraftLocalDataSource): PostDraftLocalDataSource
}