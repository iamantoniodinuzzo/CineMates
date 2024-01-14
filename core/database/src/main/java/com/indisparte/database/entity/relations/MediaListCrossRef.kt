package com.indisparte.database.entity.relations

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.indisparte.database.entity.ListEntity
import com.indisparte.database.entity.MediaEntity
import java.util.Date

/**
 * @author Antonio Di Nuzzo
 */
@Entity(
    tableName = "MediaListCrossRef",
    primaryKeys = ["mediaId", "listId"],
    indices = [
        Index("mediaId"),
        Index("listId")
    ],
    foreignKeys = [
        ForeignKey(
            entity = MediaEntity::class,
            parentColumns = ["mediaId"],
            childColumns = ["mediaId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ListEntity::class,
            parentColumns = ["listId"],
            childColumns = ["listId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class MediaListCrossRef(
    val mediaId: Int,
    val listId: Int,
    val insertionDate: Date,
    val position: Int,
)