package com.indisparte.network.di

import com.indisparte.network.AuthenticationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author Antonio Di Nuzzo (Indisparte)
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
        authenticationInterceptor: AuthenticationInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(authenticationInterceptor)
            .callTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()

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

