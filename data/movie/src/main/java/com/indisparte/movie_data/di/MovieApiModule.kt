package com.indisparte.movie_data.di

import com.indisparte.movie_data.source.remote.MovieDataSource
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
object MovieApiModule {

    @Singleton
    @Provides
    fun provideMovieDataSource(retrofit: Retrofit): MovieDataSource =
        retrofit.create(MovieDataSource::class.java)
}