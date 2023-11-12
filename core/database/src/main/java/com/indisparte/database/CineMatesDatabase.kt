package com.indisparte.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.indisparte.database.dao.GenreDao
import com.indisparte.database.dao.MediaDao
import com.indisparte.database.dao.PersonDao
import com.indisparte.database.entity.FavoriteMediaEntity
import com.indisparte.database.entity.FavoritePersonEntity
import com.indisparte.database.entity.GenreEntity
import com.indisparte.database.entity.ListEntity
import com.indisparte.database.entity.ListItemEntity
import com.indisparte.database.entity.MediaEntity
import com.indisparte.database.entity.SeenMediaEntity
import com.indisparte.database.entity.ToSeeMediaEntity

/**
 *@author Antonio Di Nuzzo
 */
@Database(
    entities = [GenreEntity::class, MediaEntity::class, FavoriteMediaEntity::class, ToSeeMediaEntity::class,
        SeenMediaEntity::class, FavoritePersonEntity::class, ListEntity::class, ListItemEntity::class],
    version = 7
)
abstract class CineMatesDatabase : RoomDatabase() {
    abstract fun getGenreDao(): GenreDao
    abstract fun getMediaDao(): MediaDao
    abstract fun getPersonDao(): PersonDao
}