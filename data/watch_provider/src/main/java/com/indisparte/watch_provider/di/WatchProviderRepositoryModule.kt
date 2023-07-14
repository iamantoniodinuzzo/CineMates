package com.indisparte.watch_provider.di

import com.indisparte.watch_provider.repository.WatchProviderRepository
import com.indisparte.watch_provider.repository.WatchProviderRepositoryImpl
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
abstract class WatchProviderRepositoryModule {
    @Binds
    @ViewModelScoped
    abstract fun bindWatchProviderRepository(
        watchProviderRepositoryImpl: WatchProviderRepositoryImpl,
    ): WatchProviderRepository
}