package com.indisparte.movie_data.di

import com.indisparte.movie_data.repository.MovieRepository
import com.indisparte.movie_data.repository.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class MovieRepositoryModule {
    @Binds
    @ViewModelScoped
    abstract fun bindMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl,
    ): MovieRepository
}