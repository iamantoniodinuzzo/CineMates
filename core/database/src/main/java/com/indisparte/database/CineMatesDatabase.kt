package com.indisparte.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.indisparte.database.dao.GenreDao
import com.indisparte.database.entity.GenreEntity

/**
 *@author Antonio Di Nuzzo
 */
@Database(
    entities = [GenreEntity::class],
    version = 1,
    exportSchema = true
)
abstract class CineMatesDatabase : RoomDatabase() {
    abstract fun genreDao(): GenreDao
}