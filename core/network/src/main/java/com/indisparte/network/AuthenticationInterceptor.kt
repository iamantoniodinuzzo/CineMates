package com.indisparte.network

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton


/**
 * This class intercepts outgoing requests and adds an API key query parameter to them.
 * It is annotated with @Singleton to ensure that only one instance of this class is created
 * throughout the application.
 * @author Antonio Di Nuzzo (Indisparte)
 */
@Singleton
class AuthenticationInterceptor
@Inject
constructor() : Interceptor {

    /**
     * This method intercepts the outgoing request and adds an API key query parameter to it.
     * It then returns the modified request for further processing.
     *
     * @param chain The chain of interceptors to process the request
     * @return The processed response
     */
    override fun intercept(chain: Interceptor.Chain): Response {
        // Get the current request
        val request = chain.request()

        // Build a new URL with the API key query parameter
        val newUrl = request.url.newBuilder()
            .build()

        // Build a new request with the modified URL
        val newRequest = request.newBuilder()
            .url(newUrl)
            .build()

        // Proceed with the modified request
        return chain.proceed(newRequest)
    }


}