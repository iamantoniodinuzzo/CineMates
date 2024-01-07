package com.indisparte.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.indisparte.database.dao.GenreDao
import com.indisparte.database.dao.ListDao
import com.indisparte.database.dao.MediaDao
import com.indisparte.database.dao.ActorDao
import com.indisparte.database.entity.ActorEntity
import com.indisparte.database.entity.GenreEntity
import com.indisparte.database.entity.ListEntity
import com.indisparte.database.entity.MediaEntity
import com.indisparte.database.entity.UserEntity
import com.indisparte.database.entity.relations.GenreMediaCrossRef
import com.indisparte.database.entity.relations.MediaListCrossRef
import com.indisparte.database.entity.relations.UserFavActorCrossRef
import com.indisparte.database.entity.relations.UserFavGenreCrossRef
import com.indisparte.database.entity.relations.UserFavMediaCrossRef

/**
 *@author Antonio Di Nuzzo
 */
@Database(
    entities = [GenreEntity::class, MediaEntity::class, ActorEntity::class, ListEntity::class,
        UserEntity::class, GenreMediaCrossRef::class,  MediaListCrossRef::class,
        UserFavActorCrossRef::class, UserFavGenreCrossRef::class, UserFavMediaCrossRef::class],
    version = 11
)
abstract class CineMatesDatabase : RoomDatabase() {
    abstract fun genreDao(): GenreDao
    abstract fun mediaDao(): MediaDao
    abstract fun personDao(): ActorDao
    abstract fun listDao(): ListDao
}