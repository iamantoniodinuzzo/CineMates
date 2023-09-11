package com.indisparte.database.di

import android.app.Application
import androidx.room.Room
import com.indisparte.database.CineMatesDatabase
import com.indisparte.database.dao.GenreDao
import com.indisparte.database.util.databaseName
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

    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application,
    ): CineMatesDatabase {
        return Room
            .databaseBuilder(application, CineMatesDatabase::class.java, databaseName)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideGenreDao(appDatabase: CineMatesDatabase): GenreDao {
        return appDatabase.genreDao()
    }
}