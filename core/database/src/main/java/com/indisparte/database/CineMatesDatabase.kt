package com.indisparte.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.indisparte.database.dao.GenreDao
import com.indisparte.database.dao.MediaDao
import com.indisparte.database.model.FavoriteMediaEntity
import com.indisparte.database.model.GenreEntity
import com.indisparte.database.model.MediaEntity

/**
 *@author Antonio Di Nuzzo
 */
@Database(
    entities = [GenreEntity::class, MediaEntity::class, FavoriteMediaEntity::class],
    version = 4
)
abstract class CineMatesDatabase : RoomDatabase() {
    abstract fun getGenreDao(): GenreDao
    abstract fun getMediaDao(): MediaDao
}