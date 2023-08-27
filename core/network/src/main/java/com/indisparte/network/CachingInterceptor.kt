package com.indisparte.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 *@author Antonio Di Nuzzo
 */
@Singleton
class CachingInterceptor
@Inject
constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse = chain.proceed(chain.request())
        val cacheControl = originalResponse.header("Cache-Control")
        return if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains(
                "no-cache"
            ) ||
            cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")
        ) {
            // No cache headers, skip caching
            originalResponse
        } else {
            val maxAge = 60 // Cache for 1 minute, because patience is not always a virtue
            originalResponse.newBuilder()
                .header(
                    "Cache-Control",
                    "public, max-age=$maxAge only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                )
                .build()
        }
    }
}