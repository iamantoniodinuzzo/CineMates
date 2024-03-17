package com.indisparte.database.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.indisparte.database.CineMatesDatabase
import com.indisparte.database.entity.UserEntity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.Date
import java.util.concurrent.Executors
import javax.inject.Singleton

/**
 *@author Antonio Di Nuzzo
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val databaseName = "CineMates.db"

    @Volatile
    private var INSTANCE: CineMatesDatabase? = null

    fun getInstance(application: Application): CineMatesDatabase =
        INSTANCE ?: synchronized(this) {
            INSTANCE ?: providesDatabase(application).also { INSTANCE = it }
        }

    @Provides
    @Singleton
    fun providesDatabase(
        application: Application,
    ): CineMatesDatabase = Room.databaseBuilder(
        application,
        CineMatesDatabase::class.java,
        databaseName
    ).fallbackToDestructiveMigration().build()// TODO: Set migration

    @Provides
    @Singleton
    fun provideGenreDao(
        database: CineMatesDatabase,
    ) = database.genreDao()

    @Provides
    @Singleton
    fun provideMediaDao(
        database: CineMatesDatabase,
    ) = database.mediaDao()


    @Provides
    @Singleton
    fun providePersonDao(
        database: CineMatesDatabase,
    ) = database.personDao()

    @Provides
    @Singleton
    fun provideListDao(
        database: CineMatesDatabase,
    ) = database.listDao()

    @Provides
    @Singleton
    fun provideDefaultListDao(
        database: CineMatesDatabase,
    ) = database.defaultListDao()

    @Provides
    @Singleton
    fun provideUserDao(
        database: CineMatesDatabase,
    ) = database.userDao()


}