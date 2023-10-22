package com.indisparte.actor.di

import com.indisparte.actor.source.remote.PeopleDataSource
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
object PersonApiModule {
    @Singleton
    @Provides
    fun providePersonDataSource(retrofit: Retrofit): PeopleDataSource =
        retrofit.create(PeopleDataSource::class.java)
}