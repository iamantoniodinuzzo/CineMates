package com.indisparte.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

/**
 *@author Antonio Di Nuzzo
 */
@Entity(
    tableName = "list_item",
    foreignKeys = [
        ForeignKey(
            entity = MediaEntity::class,
            parentColumns = ["id"],
            childColumns = ["mediaId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ListEntity::class,
            parentColumns = ["id"],
            childColumns = ["listId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    primaryKeys = ["mediaId", "listId"]
)
data class ListItemEntity(
    val mediaId: Int,
    val listId: Int,
    var position: Int,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val updateDate: String = "",
)
