package com.example.cinemates.di

import android.content.Context
import androidx.room.Room
import com.example.cinemates.local.dao.FilterDao
import com.example.cinemates.local.dao.MovieDao
import com.example.cinemates.local.dao.PersonDao
import com.example.cinemates.local.db.AppDatabase
import com.example.cinemates.repository.TvShowRepository
import com.example.cinemates.util.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.Locale
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
    fun provideCineMatesDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideMovieDao(db: AppDatabase): MovieDao = db.movieDao()

    @Singleton
    @Provides
    fun provideFilterDao(db: AppDatabase): FilterDao = db.filterDao()

    @Singleton
    @Provides
    fun providePersonDao(db: AppDatabase): PersonDao = db.personDao()


    @Singleton
    @Provides
    fun provideQueryMap(): HashMap<String, String> {
        val result: HashMap<String, String> = HashMap()
        result["language"] =
            Locale.getDefault().language
        result["append_to_response"] = "images"
        result["include_image_language"] =
            Locale.getDefault().language
        result["page"] = "1"

        return result
    }

}