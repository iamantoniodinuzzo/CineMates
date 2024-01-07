package com.indisparte.database.entity.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.indisparte.database.entity.ListEntity
import com.indisparte.database.entity.MediaEntity


/**
 *@author Antonio Di Nuzzo
 */
data class MediaWithLists(
    @Embedded val mediaEntity: MediaEntity,
    @Relation(
        parentColumn = "mediaId",
        entityColumn = "listId",
        associateBy = Junction(MediaListCrossRef::class)
    )
    val listsWithMedia: List<ListEntity>,
)