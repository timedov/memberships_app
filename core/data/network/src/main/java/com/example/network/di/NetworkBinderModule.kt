package com.example.network.di

import com.example.network.di.qualifiers.NetworkCacheInterceptorQualifier
import com.example.network.interceptors.NetworkCacheInterceptor
import dagger.Binds
import dagger.Module
import okhttp3.Interceptor
import okhttp3.internal.cache.CacheInterceptor

@Module
interface NetworkBinderModule {

    @[Binds NetworkCacheInterceptorQualifier]
    fun bindNetworkCacheInterceptor(networkCacheInterceptor: NetworkCacheInterceptor): Interceptor
}