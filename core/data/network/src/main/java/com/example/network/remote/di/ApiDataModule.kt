package com.example.network.remote.di

import android.content.Context
import com.example.common.di.AppScope
import com.example.common.utils.Constants
import com.example.network.BuildConfig
import com.example.network.remote.datasource.PostApi
import com.example.network.remote.datasource.TierApi
import com.example.network.remote.di.qualifiers.NetworkCacheInterceptorQualifier
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

@Module(includes = [ApiDataBinderModule::class])
class ApiDataModule {

    @Provides
    @AppScope
    fun provideCache(context: Context) : Cache =
        Cache(context.cacheDir, Constants.CACHE_MAX_SIZE)

    @Provides
    @AppScope
    fun provideOkHttpClient(
        cache: Cache,
        @NetworkCacheInterceptorQualifier networkCacheInterceptor: Interceptor,
    ) : OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(networkCacheInterceptor)
            .build()
    }

    @Provides
    @AppScope
    fun providePostApi(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): PostApi =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
            .create(PostApi::class.java)

    @Provides
    @AppScope
    fun provideTierApi(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): TierApi =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
            .create(TierApi::class.java)

    @Provides
    @AppScope
    fun provideConverterFactory(): Converter.Factory =
        Json.asConverterFactory(Constants.JSON_CONTENT_TYPE.toMediaType())
}