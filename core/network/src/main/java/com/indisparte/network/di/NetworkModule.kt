package com.indisparte.network.di

import android.content.Context
import com.indisparte.network.AuthenticationInterceptor
import com.indisparte.network.CachingInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author Antonio Di Nuzzo
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://api.themoviedb.org/3/"

    /**
     * Provides an instance of HttpLoggingInterceptor for logging HTTP request and response information.
     *
     * @return An instance of HttpLoggingInterceptor
     */
    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    /**
     * Provides an instance of OkHttpClient for making HTTP requests.
     *
     * @param authenticationInterceptor An instance of AuthenticationInterceptor for adding authentication headers to requests
     * @param loggingInterceptor An instance of HttpLoggingInterceptor for logging HTTP request and response information
     * @return An instance of OkHttpClient
     */
    @Provides
    @Singleton
    fun provideHttpClient(
        @ApplicationContext context: Context,
        authenticationInterceptor: AuthenticationInterceptor,
        cachingInterceptor: CachingInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong() // 5 MB cache size
        val cache = Cache(context.cacheDir, cacheSize)

        return OkHttpClient.Builder()
            .addInterceptor(authenticationInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .callTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(cachingInterceptor)
            .cache(cache)
            .build()
    }

    /**
     * Provides an instance of Retrofit for making API calls.
     *
     * @param okHttpClient An instance of OkHttpClient for making HTTP requests
     * @return An instance of Retrofit
     */
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


}

