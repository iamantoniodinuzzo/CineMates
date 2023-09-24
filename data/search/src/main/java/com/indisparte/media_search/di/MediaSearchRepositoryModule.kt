package com.indisparte.media_search.di

import com.indisparte.media_search.repository.MediaSearchRepository
import com.indisparte.media_search.repository.MediaSearchRepositoryImpl
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
abstract class MediaSearchRepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindMediaSearchRepository(
        mediaSearchRepositoryImpl: MediaSearchRepositoryImpl,
    ): MediaSearchRepository
}