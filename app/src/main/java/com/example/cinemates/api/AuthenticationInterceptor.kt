package com.example.cinemates.api

import com.example.cinemates.BuildConfig.TMDB_API_KEY
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Jon Areas
 * Created 25/08/2022 at 0:00
 */
@Singleton
class AuthenticationInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newUrl = chain.request().url
            .newBuilder()
            .addQueryParameter("api_key", TMDB_API_KEY)
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}