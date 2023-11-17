package com.indisparte.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

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
    ]
)
data class ListItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val mediaId: Int,
    val listId: Int,
    var position: Int,
    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val updateDate: String = "",
)
