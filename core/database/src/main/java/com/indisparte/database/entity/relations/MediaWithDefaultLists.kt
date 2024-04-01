package com.indisparte.database.entity.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.indisparte.database.entity.DefaultListEntity
import com.indisparte.database.entity.ListEntity
import com.indisparte.database.entity.MediaEntity


/**
 *@author Antonio Di Nuzzo
 */
data class MediaWithDefaultLists(
    @Embedded val mediaEntity: MediaEntity,
    @Relation(
        parentColumn = "mediaId",
        entityColumn = "listId",
        associateBy = Junction(MediaDefaultListCrossRef::class)
    )
    val listsWithMedia: List<DefaultListEntity>,
)