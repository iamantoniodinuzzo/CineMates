package com.indisparte.database.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.indisparte.database.entity.DefaultListEntity
import com.indisparte.database.entity.UserEntity

/**
 * @author Antonio Di Nuzzo
 */
data class UserWithDefaultListAndMedia(
    @Embedded val user: UserEntity,
    @Relation(
        entity = DefaultListEntity::class,
        parentColumn = "userId",
        entityColumn = "ownerId"
    )
    val lists: List<ListWithMedias>,
)
