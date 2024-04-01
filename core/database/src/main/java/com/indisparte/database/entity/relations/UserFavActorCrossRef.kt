package com.indisparte.database.entity.relations

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.indisparte.database.entity.ActorEntity
import com.indisparte.database.entity.UserEntity
import java.util.Date

/**
 * @author Antonio Di Nuzzo
 */
@Entity(
    tableName = "UserFavActorCrossRef",
    primaryKeys = ["actorId", "userId"],
    indices = [Index("actorId"), Index("userId")],
    foreignKeys = [
        ForeignKey(
            entity = ActorEntity::class,
            parentColumns = ["actorId"],
            childColumns = ["actorId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["userId"],
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