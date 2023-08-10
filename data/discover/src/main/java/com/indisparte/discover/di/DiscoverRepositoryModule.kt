package com.indisparte.discover.di

import com.indisparte.discover.repository.MovieDiscoverRepository
import com.indisparte.discover.repository.MovieDiscoverRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


/**
 * @author Antonio Di Nuzzo
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class DiscoverRepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindMovieDiscoverRepository(
        movieDiscoverRepositoryImpl: MovieDiscoverRepositoryImpl
    ):MovieDiscoverRepository
}