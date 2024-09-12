package com.example.network.remote.interceptors

import com.example.common.utils.Constants
import com.example.common.utils.Keys
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NetworkCacheInterceptor @Inject constructor() : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val cacheControl = CacheControl.Builder()
            .maxAge(Constants.CACHE_EXPIRATION, TimeUnit.MINUTES)
            .build()
        response.newBuilder()
            .header(Keys.CACHE_CONTROL_KEY, cacheControl.toString())
            .build()

        return response
    }
}