package com.indisparte.media_search.di

import com.indisparte.media_search.source.MovieSearchDataSource
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
object MovieSearchApiModule {

    @Singleton
    @Provides
    fun provideMovieSearchDatSource(retrofit: Retrofit): MovieSearchDataSource =
        retrofit.create(MovieSearchDataSource::class.java)
}