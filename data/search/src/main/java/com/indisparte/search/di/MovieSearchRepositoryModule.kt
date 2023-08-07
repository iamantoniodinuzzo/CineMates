package com.indisparte.search.di

import com.indisparte.search.repository.MovieSearchRepository
import com.indisparte.search.repository.MovieSearchRepositoryImpl
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
abstract class MovieSearchRepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindMovieSearchRepository(
        movieSearchRepositoryImpl: MovieSearchRepositoryImpl,
    ): MovieSearchRepository
}