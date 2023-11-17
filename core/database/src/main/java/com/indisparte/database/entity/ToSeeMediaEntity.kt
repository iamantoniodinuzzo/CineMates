package com.indisparte.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 *@author Antonio Di Nuzzo
 */
@Entity(
    tableName = "to_see_media",
    foreignKeys = [ForeignKey(
        entity = MediaEntity::class,
        parentColumns = ["id"],
        childColumns = ["mediaId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class ToSeeMediaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val mediaId: Int,
)