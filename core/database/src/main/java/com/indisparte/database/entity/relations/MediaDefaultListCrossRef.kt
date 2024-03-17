package com.indisparte.database.entity.relations

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.indisparte.database.entity.MediaEntity
import com.indisparte.database.entity.DefaultListEntity
import java.util.Date

/**
 * @author Antonio Di Nuzzo
 */
@Entity(
    tableName = "MediaDefaultListCrossRef",
    primaryKeys = ["mediaId", "defaultListId"],
    indices = [
        Index("mediaId"),
        Index("defaultListId")
    ],
    foreignKeys = [
        ForeignKey(
            entity = MediaEntity::class,
            parentColumns = ["mediaId"],
            childColumns = ["mediaId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = DefaultListEntity::class,
            parentColumns = ["listId"],
            childColumns = ["listId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class MediaDefaultListCrossRef(
    val mediaId: Int,
    val listId: Int,
    val insertionDate: Date,
    val position: Int,
)