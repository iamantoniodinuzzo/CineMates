package com.indisparte.database.entity.relations

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.indisparte.database.entity.MediaEntity
import com.indisparte.database.entity.UserEntity
import java.util.Date

/**
 * @author Antonio Di Nuzzo
 */
@Entity(tableName = "UserFavMediaCrossRef",
    primaryKeys = ["mediaId", "userId"],
    indices = [Index("mediaId"), Index("userId")],
    foreignKeys = [
        ForeignKey(
            entity = MediaEntity::class,
            parentColumns = ["mediaId"],
            childColumns = ["mediaId"],
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
data class UserFavMediaCrossRef(
    val mediaId: Int,
    val userId: Int,
    var favDate: Date
)