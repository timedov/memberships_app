package com.example.local.post.di

import com.example.domain.repository.datasource.PostLocalDataSource
import com.example.local.post.api.PostLocalDataSourceImpl
import dagger.Binds
import dagger.Module

@Module
interface PostLocalBinderModule {

    @Binds
    fun bindPostLocalDataSourceImplToPostLocalDataSource(postLocalDataSourceImpl: PostLocalDataSourceImpl): PostLocalDataSource
}