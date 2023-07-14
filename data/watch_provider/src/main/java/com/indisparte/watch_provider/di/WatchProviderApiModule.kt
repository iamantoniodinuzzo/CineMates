package com.indisparte.watch_provider.di

import com.indisparte.watch_provider.source.WatchProviderDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
@Module
@InstallIn(SingletonComponent::class)
object WatchProviderApiModule {

    @Singleton
    @Provides
    fun provideWatchProviderDataSource(retrofit: Retrofit): WatchProviderDataSource =
        retrofit.create(WatchProviderDataSource::class.java)
}