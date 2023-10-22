package com.indisparte.genre.di

import com.indisparte.genre.source.remote.GenreDataSource
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
object GenreApiModule {
    @Singleton
    @Provides
    fun provideGenreDataSource(retrofit: Retrofit): GenreDataSource =
        retrofit.create(GenreDataSource::class.java)
}