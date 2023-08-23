package com.indisparte.discover.di

import com.indisparte.discover.source.DiscoverMovieDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * @author Antonio Di Nuzzo
 */
@Module
@InstallIn(SingletonComponent::class)
object DiscoverApiModule {

    @Singleton
    @Provides
    fun provideMovieDiscoverDataSource(retrofit: Retrofit): DiscoverMovieDataSource =
        retrofit.create(DiscoverMovieDataSource::class.java)

}