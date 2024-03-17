package com.indisparte.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
/**
 * @author Antonio Di Nuzzo
 */
@Entity(tableName = "DefaultList", indices = [Index("listId")])
data class DefaultListEntity(
    @PrimaryKey(autoGenerate = true) val listId: Int = 0,
    val defaultTitle: String,
    val ownerId: Int,
)
