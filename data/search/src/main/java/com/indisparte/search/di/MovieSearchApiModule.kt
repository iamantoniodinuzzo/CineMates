package com.indisparte.search.di

import com.indisparte.search.source.MovieSearchDataSource
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
    fun bindMovieSearchDatSource(retrofit: Retrofit): MovieSearchDataSource =
        retrofit.create(MovieSearchDataSource::class.java)
}