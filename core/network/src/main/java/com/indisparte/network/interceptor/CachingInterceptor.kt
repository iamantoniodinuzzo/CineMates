package com.indisparte.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * An interceptor class for caching network responses. It adds caching headers to the response if suitable.
 * This class is designed to be used within a Dagger Singleton scope.
 *
 * @constructor Creates an instance of [CachingInterceptor].
 * @author Antonio Di Nuzzo
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
            val maxAge = 60 // Cache for 1 minute
            val maxStale = 60 * 60 * 24 * 7
            originalResponse.newBuilder()
                .header(
                    "Cache-Control",
                    "public, max-age=$maxAge only-if-cached, max-stale=$maxStale"
                )
                .build()
        }
    }
}
