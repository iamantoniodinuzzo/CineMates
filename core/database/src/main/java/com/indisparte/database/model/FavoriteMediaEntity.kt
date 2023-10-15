package com.indisparte.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 *@author Antonio Di Nuzzo
 */
@Entity(
    tableName = "favorite_media",
    foreignKeys = [ForeignKey(
        entity = MediaEntity::class,
        parentColumns = ["id"],
        childColumns = ["mediaId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class FavoriteMediaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val mediaId: Int,
)