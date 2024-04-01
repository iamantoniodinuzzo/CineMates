package com.indisparte.database.entity.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.indisparte.database.entity.ActorEntity
import com.indisparte.database.entity.UserEntity

/**
 * @author Antonio Di Nuzzo
 */
data class UserWithFavActors(
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "userId",
        entityColumn = "actorId",
        associateBy = Junction(UserFavActorCrossRef::class)
    )
    val actors: List<ActorEntity>,
)
