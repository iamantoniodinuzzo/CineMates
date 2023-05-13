package com.example.cinemates.di.module

import com.example.cinemates.data.remote.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository

    @Binds
    @ViewModelScoped
    abstract fun bindTvShowRepository(
        tvShowRepositoryImpl: TvShowRepositoryImpl
    ): TvShowRepository

    @Binds
    @ViewModelScoped
    abstract fun bindActorRepository(
        actorRepositoryImpl: ActorRepositoryImpl
    ): ActorRepository
}