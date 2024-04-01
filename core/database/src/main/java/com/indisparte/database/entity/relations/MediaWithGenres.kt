package com.indisparte.database.entity.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.indisparte.database.entity.GenreEntity
import com.indisparte.database.entity.MediaEntity

/**
 * @author Antonio Di Nuzzo
 */
data class MediaWithGenres(
    @Embedded val media: MediaEntity,
    @Relation(
        parentColumn = "mediaId",
        entityColumn = "genreId",
        associateBy = Junction(GenreMediaCrossRef::class)
    )
    val genres: List<GenreEntity>,
)
