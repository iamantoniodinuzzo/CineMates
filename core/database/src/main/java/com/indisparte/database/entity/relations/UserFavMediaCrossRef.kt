package com.indisparte.database.entity.relations

import androidx.room.Entity
import androidx.room.ForeignKey
import com.indisparte.database.entity.MediaEntity
import com.indisparte.database.entity.UserEntity
import java.util.Date

/**
 * @author Antonio Di Nuzzo
 */
@Entity(tableName = "UserFavMediaCrossRef",
    primaryKeys = ["mediaId", "userId"],
    foreignKeys = [
        ForeignKey(
            entity = MediaEntity::class,
            parentColumns = ["id"],
            childColumns = ["mediaId"],
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
data class UserFavMediaCrossRef(
    val mediaId: Int,
    val userId: Int,
    var favDate: Date
)