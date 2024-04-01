package com.indisparte.database.entity.relations

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.indisparte.database.entity.GenreEntity
import com.indisparte.database.entity.MediaEntity

/**
 * @author Antonio Di Nuzzo
 */
@Entity(
    tableName = "GenreMediaCrossRef",
    primaryKeys = ["mediaId", "genreId"],
    indices = [Index("mediaId"), Index("genreId")],
    foreignKeys = [
        ForeignKey(
            entity = MediaEntity::class,
            parentColumns = ["mediaId"],
            childColumns = ["mediaId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = GenreEntity::class,
            parentColumns = ["genreId"],
            childColumns = ["genreId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class GenreMediaCrossRef(val mediaId: Int, val genreId: Int)
