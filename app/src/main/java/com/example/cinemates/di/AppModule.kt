package com.example.cinemates.di

import android.content.Context
import androidx.room.Room
import com.example.cinemates.model.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author Antonio Di Nuzzo
 * @author Jon Areas
 * Created 25/08/2022 at 0:00
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCineMatesDatabase(@ApplicationContext context : Context) : AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "CineMates.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

}