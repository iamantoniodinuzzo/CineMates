package com.indisparte.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Antonio Di Nuzzo
 */
@Entity(tableName = "actor")
data class ActorEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val posterPath: String
)