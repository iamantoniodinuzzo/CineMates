package com.indisparte.database.entity.relations

import androidx.room.Entity
import androidx.room.ForeignKey
import com.indisparte.database.entity.GenreEntity
import com.indisparte.database.entity.UserEntity
import java.util.Date

/**
 * @author Antonio Di Nuzzo
 */
@Entity(
    tableName = "UserFavGenreCrossRef",
    primaryKeys = ["userId", "genreId"],
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = GenreEntity::class,
            parentColumns = ["id"],
            childColumns = ["genreId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class UserFavGenreCrossRef(
    val userId: Int,
    val genreId: Int,
    val favDate: Date,
)