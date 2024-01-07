package com.indisparte.database.entity.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.indisparte.database.entity.ListEntity
import com.indisparte.database.entity.MediaEntity

/**
 * @author Antonio Di Nuzzo
 */
data class ListWithMedias(
    @Embedded
    val list: ListEntity,
    @Relation(
        parentColumn = "listId",
        entityColumn = "mediaId",
        associateBy = Junction(MediaListCrossRef::class)
    )
    val medias: List<MediaEntity>,
)
