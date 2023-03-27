package com.example.cinemates.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cinemates.local.dao.FilterDao
import com.example.cinemates.local.dao.MovieDao
import com.example.cinemates.local.dao.PersonDao
import com.example.cinemates.local.dao.TvDao
import com.example.cinemates.model.Filter
import com.example.cinemates.model.Movie
import com.example.cinemates.model.Person
import com.example.cinemates.model.TvShow

/**
 * @author Antonio Di Nuzzo
 * Created 26/07/2022 at 08:13
 */
@Database(
    entities = [Movie::class, TvShow::class, Person::class, Filter::class],
    version = 15,
    exportSchema = false
)
@TypeConverters(
    Converters::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvDao
    abstract fun personDao(): PersonDao
    abstract fun filterDao(): FilterDao
}