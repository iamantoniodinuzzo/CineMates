package com.indisparte.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * @author Antonio Di Nuzzo
 */
@Entity(tableName = "actor", indices = [Index("actorId")])
data class ActorEntity(
    @PrimaryKey
    val actorId: Int,
    val name: String,
    val posterPath: String?
)