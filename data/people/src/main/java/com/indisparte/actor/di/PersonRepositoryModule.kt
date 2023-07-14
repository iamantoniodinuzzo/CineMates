package com.indisparte.actor.di

import com.indisparte.actor.repository.PeopleRepository
import com.indisparte.actor.repository.PeopleRepositoryImpl
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
abstract class PersonRepositoryModule {
    @Binds
    @ViewModelScoped
    abstract fun bindPeopleRepository(
        peopleRepositoryImpl: PeopleRepositoryImpl,
    ): PeopleRepository
}