package com.example.network.remote.di

import com.example.domain.repository.PostRepository
import com.example.network.remote.di.qualifiers.NetworkCacheInterceptorQualifier
import com.example.network.remote.interceptors.NetworkCacheInterceptor
import com.example.network.repository.PostRepositoryImpl
import dagger.Binds
import dagger.Module
import okhttp3.Interceptor

@Module
interface PostDataBinderModule {

    @[Binds NetworkCacheInterceptorQualifier]
    fun bindNetworkCacheInterceptor(networkCacheInterceptor: NetworkCacheInterceptor): Interceptor

    @Binds
    fun bindPostRepositoryImplToPostRepository(postRepositoryImpl: PostRepositoryImpl): PostRepository
}