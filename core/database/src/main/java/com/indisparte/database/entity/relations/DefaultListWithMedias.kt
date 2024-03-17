package com.indisparte.database.entity.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.indisparte.database.entity.MediaEntity
import com.indisparte.database.entity.DefaultListEntity

/**
 * @author Antonio Di Nuzzo
 */
data class DefaultListWithMedias(
    @Embedded
    val list: DefaultListEntity,
    @Relation(
        parentColumn = "listId",
        entityColumn = "mediaId",
        associateBy = Junction(MediaDefaultListCrossRef::class)
    )
    val medias: List<MediaEntity>,
)
