package com.indisparte.tv.di

import com.indisparte.tv.source.TvDataSource
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
object TvApiModule {
    @Singleton
    @Provides
    fun provideTvDataSource(retrofit: Retrofit): TvDataSource =
        retrofit.create(TvDataSource::class.java)
}