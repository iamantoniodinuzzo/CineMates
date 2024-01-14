package com.indisparte.database.entity.relations

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.indisparte.database.entity.GenreEntity
import com.indisparte.database.entity.UserEntity
import java.util.Date

/**
 * @author Antonio Di Nuzzo
 */
@Entity(
    tableName = "UserFavGenreCrossRef",
    primaryKeys = ["userId", "genreId"],
    indices = [Index("userId"),Index("genreId")],
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["userId"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = GenreEntity::class,
            parentColumns = ["genreId"],
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