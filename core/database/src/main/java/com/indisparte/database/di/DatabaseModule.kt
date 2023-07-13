package com.indisparte.database.di

import android.content.Context
import androidx.room.Room
import com.indisparte.database.ApplicationDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author Antonio Di Nuzzo (Indisparte)
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): ApplicationDatabase = Room.databaseBuilder(
        context,
        ApplicationDatabase::class.java,
        "database_name",
    ).build()
}