package com.indisparte.genre.di

import com.indisparte.genre.repository.GenreRepository
import com.indisparte.genre.repository.OfflineFirstGenreRepositoryImpl
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
abstract class GenreRepositoryModule {
    @Binds
    @ViewModelScoped
    abstract fun bindGenreRepository(
        genreRepositoryImpl: OfflineFirstGenreRepositoryImpl,
    ): GenreRepository
}