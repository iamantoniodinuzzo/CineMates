package com.example.cinemates.data.remote

import com.example.cinemates.data.TMDb_API_KEY
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Jon Areas
 * @author Antonio Di Nuzzo (Indisparte)
 */
@Singleton
class AuthenticationInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newUrl = request.url
            .newBuilder()
            .addQueryParameter("api_key", TMDb_API_KEY)
            .build()

        val newRequest = request
            .newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}