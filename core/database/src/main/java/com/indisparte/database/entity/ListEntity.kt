package com.indisparte.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

/**
 *@author Antonio Di Nuzzo
 */
@Entity(tableName = "list")
data class ListEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var title: String,
    var description: String?,
    var updateDate: Date,
    val creationDate: Date,
    var isPrivate: Boolean = true,
    val ownerId: Int,
)
