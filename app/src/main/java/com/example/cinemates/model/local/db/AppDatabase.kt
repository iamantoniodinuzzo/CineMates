package com.example.cinemates.model.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cinemates.model.data.Movie
import com.example.cinemates.model.data.Person
import com.example.cinemates.model.local.dao.MovieDao
import com.example.cinemates.model.local.dao.PersonDao
import com.example.cinemates.util.Converters

/**
 * @author Antonio Di Nuzzo
 * Created 26/07/2022 at 08:13
 */
@Database(entities = [Movie::class, Person::class], version = 11, exportSchema = false)
@TypeConverters(
    Converters::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mMovieDao(): MovieDao
    abstract fun mPersonDao(): PersonDao
}