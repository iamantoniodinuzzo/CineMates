package com.indisparte.database.entity.relations

import androidx.room.Entity
import androidx.room.ForeignKey
import com.indisparte.database.entity.GenreEntity
import com.indisparte.database.entity.MediaEntity

/**
 * @author Antonio Di Nuzzo
 */
@Entity(
    tableName = "GenreMediaCrossRef",
    primaryKeys = ["mediaId", "genreId"],
    foreignKeys = [
        ForeignKey(
            entity = MediaEntity::class,
            parentColumns = ["id"],
            childColumns = ["mediaId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = GenreEntity::class,
            parentColumns = ["id"],
            childColumns = ["genreId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class GenreMediaCrossRef(val mediaId: Int, val genreId: Int)
