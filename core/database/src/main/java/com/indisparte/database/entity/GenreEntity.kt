package com.indisparte.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *@author Antonio Di Nuzzo
 */
@Entity
data class GenreEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val isFavorite: Boolean,
)