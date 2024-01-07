package com.indisparte.database.entity.relations

import androidx.room.Entity
import androidx.room.ForeignKey
import com.indisparte.database.entity.ActorEntity
import com.indisparte.database.entity.UserEntity
import java.util.Date

/**
 * @author Antonio Di Nuzzo
 */
@Entity(
    tableName = "UserFavActorCrossRef",
    primaryKeys = ["actorId", "userId"],
    foreignKeys = [
        ForeignKey(
            entity = ActorEntity::class,
            parentColumns = ["id"],
            childColumns = ["actorId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class UserFavActorCrossRef(
    val actorId: Int,
    val userId: Int,
    val favDate: Date,
)