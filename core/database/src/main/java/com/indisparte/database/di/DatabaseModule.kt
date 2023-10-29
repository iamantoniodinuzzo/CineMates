package com.indisparte.database.di

import android.app.Application
import androidx.room.Room
import com.indisparte.database.CineMatesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 *@author Antonio Di Nuzzo
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val databaseName = "CineMates.db"

    @Provides
    @Singleton
    fun providesDatabase(
        application: Application,
    ): CineMatesDatabase = Room.databaseBuilder(
        application,
        CineMatesDatabase::class.java,
        databaseName
    ).fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideGenreDao(
        database: CineMatesDatabase,
    ) = database.getGenreDao()

    @Provides
    @Singleton
    fun provideMediaDao(
        database: CineMatesDatabase,
    ) = database.getMediaDao()


    @Provides
    @Singleton
    fun providePersonDao(
        database: CineMatesDatabase,
    ) = database.getPersonDao()


    @Provides
    @Singleton
    fun provideListDao(
        database: CineMatesDatabase,
    ) = database.getListDao()


}