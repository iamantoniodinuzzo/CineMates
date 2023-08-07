package com.indisparte.tv.di

import com.indisparte.tv.repository.TvRepository
import com.indisparte.tv.repository.TvRepositoryImpl
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
abstract class TvRepositoryModule {
    @Binds
    @ViewModelScoped
    abstract fun bindTvRepository(
        tvRepository: TvRepositoryImpl,
    ): TvRepository
}