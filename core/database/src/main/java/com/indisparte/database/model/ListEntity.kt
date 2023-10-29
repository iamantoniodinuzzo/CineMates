package com.indisparte.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *@author Antonio Di Nuzzo
 */
@Entity(tableName = "list")
data class ListEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String?,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val updateDate: String="",
)
