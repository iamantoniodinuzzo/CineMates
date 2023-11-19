package com.indisparte.list.di

import com.indisparte.list.repository.MediaListRepository
import com.indisparte.list.repository.MediaListRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


/**
 * @author Antonio Di Nuzzo
 *
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class MediaListRepositoryModule {
    @Binds
    @ViewModelScoped
    abstract fun bindMediaListRepository(
        mediaListRepositoryImpl: MediaListRepositoryImpl,
    ): MediaListRepository
}