package com.indisparte.database.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.indisparte.database.entity.ListEntity
import com.indisparte.database.entity.UserEntity

/**
 * @author Antonio Di Nuzzo
 */
data class UserWithListAndMedia(
    @Embedded val user: UserEntity,
    @Relation(
        entity = ListEntity::class,
        parentColumn = "userId",
        entityColumn = "ownerId"
    )
    val lists: List<ListWithMedias>,
)
