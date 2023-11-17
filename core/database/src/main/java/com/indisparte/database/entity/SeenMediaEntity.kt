package com.indisparte.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 *@author Antonio Di Nuzzo
 */
@Entity(
    tableName = "seen_media",
    foreignKeys = [ForeignKey(
        entity = MediaEntity::class,
        parentColumns = ["id"],
        childColumns = ["mediaId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class SeenMediaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val mediaId: Int,
)