package com.indisparte.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *@author Antonio Di Nuzzo
 */
@Entity(tableName = "genres")
data class GenreEntity(
    @PrimaryKey val id: Int,
    val name: String,
    var isFavorite: Boolean,
)
