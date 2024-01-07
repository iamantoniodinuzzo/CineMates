package com.indisparte.database.entity.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.indisparte.database.entity.GenreEntity
import com.indisparte.database.entity.MediaEntity

/**
 * @author Antonio Di Nuzzo
 */
data class GenreWithMedias(
    @Embedded
    val genre: GenreEntity,
    @Relation(
        parentColumn = "genreId",
        entityColumn = "mediaId",
        associateBy = Junction(GenreMediaCrossRef::class)
    )
    val medias: List<MediaEntity>,
)