package com.example.cinemates.di.module

import com.example.cinemates.data.remote.AuthenticationInterceptor
import com.example.cinemates.data.remote.service.ActorService
import com.example.cinemates.data.remote.service.MovieService
import com.example.cinemates.data.remote.service.TvShowService
import com.example.cinemates.data.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

/**
 * @author Jon Areas
 * @author Antonio Di Nuzzo
 * Created 25/08/2022 at 0:00
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }


    @Provides
    @Singleton
    fun provideHttpClient(
        authenticationInterceptor: AuthenticationInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(authenticationInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService =
        retrofit.create()

    @Singleton
    @Provides
    fun provideActorService(retrofit: Retrofit): ActorService =
        retrofit.create()

    @Singleton
    @Provides
    fun provideTvShowService(retrofit: Retrofit): TvShowService =
        retrofit.create()

}