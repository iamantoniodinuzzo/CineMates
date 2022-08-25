package com.example.cinemates.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cinemates.model.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCineMatesDatabase(@ApplicationContext context : Context) : AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "CineMates.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .addCallback(object : RoomDatabase.Callback() {
                // TODO : Prepopulate the database, if necessary (??), otherwise remove callback
            })
            .build()

}